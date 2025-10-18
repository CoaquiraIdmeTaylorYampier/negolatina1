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
fun RegisterScreen(navController: androidx.navigation.NavController) {
    val fullname = remember { mutableStateOf("") }
    val user = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Registrate", modifier = Modifier.padding(bottom = 12.dp))
            OutlinedTextField(value = fullname.value, onValueChange = { fullname.value = it }, label = { Text("Nombre completo") })
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = user.value, onValueChange = { user.value = it }, label = { Text("Usuario") })
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = password.value, onValueChange = { password.value = it }, label = { Text("Contraseña") })
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                // mock registration; navigate to onboarding sell
                navController.navigate("onboarding_sell") {
                    popUpTo("register") { inclusive = true }
                }
            }) {
                Text("Registrarme")
            }
        }
    }
}
