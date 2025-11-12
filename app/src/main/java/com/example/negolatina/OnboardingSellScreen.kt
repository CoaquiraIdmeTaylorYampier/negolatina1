package com.example.negolatina

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.negolatina.ui.theme.NegolatinaTheme

@Preview(showBackground = true, name = "Sell screen")
@Composable
fun OnboardingSellScreen() {
    NegolatinaTheme {
        OnboardingSellScreen(navController = rememberNavController())
    }
}
@Composable
fun OnboardingSellScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize().background(Color.White),
        contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)) {
            Image(painter = painterResource(id = R.drawable.bienvenida_venta),
                contentDescription = "ilustracion de venta ",
                modifier = Modifier.size(450.dp))
            Text("Podrás crear tu propia cuenta donde podrás vender tus productos a un buen precio",
                color = Color.Red,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp))

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("onboarding_buy") },
                colors = ButtonDefaults.buttonColors(containerColor=Color.Black)) {
                Text("Finalizar")
            }
        }
    }
}
