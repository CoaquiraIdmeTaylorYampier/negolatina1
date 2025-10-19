package com.example.negolatina

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(navController: androidx.navigation.NavController) {
    val user = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Iniciar sesión", modifier = Modifier.padding(bottom = 12.dp))
            OutlinedTextField(value = user.value, onValueChange = { user.value = it }, label = { Text("Usuario") })
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = password.value, onValueChange = { password.value = it }, label = { Text("Contraseña") })
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                // simple validation; in real app call ViewModel
                if (user.value.isNotBlank()) {
                    // navigate to home (mock)
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }) {
                Text("Iniciar sesión")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("¿No tienes cuenta? Registrate aquí", modifier = Modifier.padding(top = 8.dp))
            Button(onClick = { navController.navigate("register") }, modifier = Modifier.padding(top = 8.dp)) {
                Text("Registrate")
            }
        }
    }
}
