package com.example.negolatina

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            title = "Mi cuenta",
            onBackClicked = { navController.popBackStack() },
            onEditClicked = { /* aun por configurar */ }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ClientProfileHeader()
            Spacer(modifier = Modifier.height(24.dp))
            ClientDataSection()
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* cerrar sesión */ },
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
private fun ClientProfileHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Profile Picture",
            modifier = Modifier.size(80.dp),
            tint = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Cliente", color = Color.White,
            fontSize = 16.sp, fontWeight = FontWeight.Medium)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ClientDataSection() {
    val textFieldColors = OutlinedTextFieldDefaults.colors(

        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,

        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Black,

        focusedLabelColor = Color.White,
        unfocusedLabelColor = Color.White,

        focusedBorderColor = Color.White,
        unfocusedBorderColor = Color.White.copy(alpha = 0.7f),

        cursorColor = Color.Red
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = "usuario name",
            onValueChange = {},
            label = { Text("Nombre completo") },
            colors = textFieldColors,
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "usuario@cliente.com",
            onValueChange = {},
            label = { Text("Correo electrónico") },
            colors = textFieldColors,
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )
        OutlinedTextField(
            value = "avenida....",
            onValueChange = {},
            label = { Text("Direccion") },
            colors = textFieldColors,
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
            
        )
    }
}
