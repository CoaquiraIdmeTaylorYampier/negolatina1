package com.example.negolatina

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.negolatina.ui.theme.NegolatinaTheme


@Preview(showBackground = true, name = "Buy screen")
@Composable
fun OnboardingBuyScreen() {
    NegolatinaTheme {
        OnboardingBuyScreen(navController = rememberNavController())
    }
}
@Composable
fun OnboardingBuyScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
            Text("Podrás comprar distintos productos a un precio muy bueno", modifier = Modifier.padding(8.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("home") }) {
                Text("Finalizar")
            }
        }
    }
}
