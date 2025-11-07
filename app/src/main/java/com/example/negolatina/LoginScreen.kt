package com.example.negolatina

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
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
                    if (user.value.isNotBlank()) {
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
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
        }
    }
}
