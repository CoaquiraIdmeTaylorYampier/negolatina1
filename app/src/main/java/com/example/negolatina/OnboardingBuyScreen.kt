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


@Preview(showBackground = true, name = "Buy screen")
@Composable
fun OnboardingBuyScreenPreview() {
    NegolatinaTheme {
        OnboardingBuyScreen(navController = rememberNavController())
    }
}

@Composable
fun OnboardingBuyScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, 
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                "¡PRECIO Y PESO JUSTO!",
                color = Color.Red,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))

            Image(painter = painterResource(id = R.drawable.bienvenidad_compra),
                contentDescription = "Ilustración de compra",
                modifier = Modifier.size(300.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "SOMOS UNA EMPRESA PUNEÑA CON MAS DE 20 AÑOS DE EXPERIENCIA LLEVANDO PRODUCTOS ALIMENTICIOS DE LA MEJOR CALIDAD A TU HOGAR.",
                color = Color.Red,
                fontSize = 18.sp,
                fontWeight = FontWeight.W900,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { navController.navigate("home") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("Finalizar", modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp))
            }
        }
    }
}
