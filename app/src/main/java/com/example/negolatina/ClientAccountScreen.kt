package com.example.negolatina

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
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
@Preview(showBackground = true, name = "Cuenta del Cliente")
@Composable
fun ClientAccountScreenPreview() {
    NegolatinaTheme {
        ClientAccountScreen(navController = rememberNavController())
    }
}
@Composable
fun ClientAccountScreen(navController: NavController) {
    Column(Modifier.fillMaxSize()) {
        AccountAppBar(
            title = "Mi cuenta Cliente",
            onBackClicked = { navController.popBackStack() },
            onEditClicked = {  }
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
                userRole = "Cliente"
            )
            Spacer(modifier = Modifier.height(24.dp))

            ClientAddressSection()
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {  },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Icon(Icons.Default.ExitToApp, contentDescription = "Cerrar sesión", tint = Color.Red)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Cerrar sesión", color = Color.Red)
            }
        }
    }
}

@Composable
private fun ClientAddressSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Dirección",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = "", onValueChange = {},
            modifier = Modifier.fillMaxWidth(), readOnly = true)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = "", onValueChange = {},
            modifier = Modifier.fillMaxWidth(), readOnly = true)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = "", onValueChange = {},
            modifier = Modifier.fillMaxWidth(), readOnly = true)
    }
}

