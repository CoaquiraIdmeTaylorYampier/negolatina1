package com.example.negolatina

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.negolatina.ui.theme.NegolatinaTheme


@Preview(showBackground = true, name = "Cuenta del admin")
@Composable
fun AdminAccountScreenPreview() {
    NegolatinaTheme {
        AdminAccountScreen(navController = rememberNavController())
    }
}
@Composable
fun AdminAccountScreen(navController: NavController) {
    Column(Modifier.fillMaxSize()) {
        AccountAppBar(
            title = "Mi cuenta Admin",
            onBackClicked = { navController.popBackStack() },
            onEditClicked = { /* TODO: Handle edit */ }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileHeader(
                userName = "Emerson A. F",
                userEmail = "user@email.com",
                userRole = "Administrador"
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth(), readOnly = true)
            Spacer(modifier = Modifier.height(24.dp))

            AdminProductsSection()
        }
    }
}

@Composable
private fun AdminProductsSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Mis productos",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
        ) {
            // añadir lista de productos! no olvidar!
        }
    }
}
