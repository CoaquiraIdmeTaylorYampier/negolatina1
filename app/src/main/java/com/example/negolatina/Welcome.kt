package com.example.negolatina

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreen(navController: androidx.navigation.NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.ic_negolatina_logo), contentDescription = "logo", modifier = Modifier.size(120.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text("Descubre y compra productos desde tu móvil: rápido, seguro y con ofertas especiales.")
            Spacer(modifier = Modifier.height(24.dp))
            Row {
                Button(onClick = { navController.navigate("login") }, modifier = Modifier.padding(8.dp)) {
                    Text("Iniciar sesión")
                }
                Button(onClick = { navController.navigate("register") }, modifier = Modifier.padding(8.dp)) {
                    Text("Registrate")
                }
            }
        }
    }
}
