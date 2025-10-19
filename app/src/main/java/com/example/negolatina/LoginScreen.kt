package com.example.negolatina

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.negolatina.ui.theme.NegolatinaTheme


@Preview(showBackground = true, name = "Login screen")
@Composable
fun LoginScreen() {
    NegolatinaTheme {
        LoginScreen(navController = rememberNavController())
    }
}
@Composable
fun LoginScreen(navController: NavController) {
    val user = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Iniciar sesión",
                modifier = Modifier.padding(bottom = 12.dp))
            OutlinedTextField(value = user.value,
                onValueChange = { user.value = it },
                label = { Text("Usuario") })
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Contraseña") })
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (user.value.isNotBlank()) {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }) {
                Text("Iniciar sesión")
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text("¿No tienes cuenta? Registrate aquí",
                modifier = Modifier.padding(top = 8.dp))
            Button(onClick = { navController.navigate("register") },
                modifier = Modifier.padding(top = 8.dp)) {
                Text("Registrate")
            }
        }
    }
}
