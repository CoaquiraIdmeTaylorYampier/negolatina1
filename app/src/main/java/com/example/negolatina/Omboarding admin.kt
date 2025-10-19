package com.example.negolatina

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingSellScreen(navController: androidx.navigation.NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
            Text("Podrás crear tu propia cuenta donde podrás vender tus productos a un buen precio", modifier = Modifier.padding(8.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("onboarding_buy") }) {
                Text("Finalizar")
            }
        }
    }
}
