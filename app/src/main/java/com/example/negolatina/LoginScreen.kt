package com.example.negolatina

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.negolatina.ui.theme.NegolatinaTheme


@Preview(showBackground = true, name = "Login screen")
@Composable
fun LoginScreenPreview() {
    NegolatinaTheme {
        LoginScreen(navController = rememberNavController())
    }
}
@Composable
fun LoginScreen(navController: NavController) {
    val user = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.iniciar_sesion),
                contentDescription = "imagen de venta ",
                modifier = Modifier.size(250.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Iniciar sesión",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            OutlinedTextField(
                value = user.value,
                onValueChange = { user.value = it },
                label = { Text("Usuario") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    if (user.value.equals("admin", ignoreCase = true)) {
                        navController.navigate("home?isAdmin=true") {
                            popUpTo("welcome") { inclusive = true }
                        }
                    } else {
                        navController.navigate("onboarding_buy") {
                            popUpTo("welcome") { inclusive = true }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE31E24)),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("INICIAR SESIÓN", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("¿No tienes cuenta?")
            Button(onClick = { navController.navigate("register") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("REGÍSTRATE")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
