package com.example.negolatina

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
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

@Preview(showBackground = true, name = "Cuenta del admin")
@Composable
fun AdminAccountScreenPreview() {
    NegolatinaTheme {
        AdminAccountScreen(navController = rememberNavController())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminAccountScreen(navController: NavController) {
    Column(Modifier.fillMaxSize()) {

        TopAppBar(
            title = { Text("Mi cuenta Admin", color = Color.White) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, "Back", tint = Color.White)
                }
            },
            actions = {
                IconButton(onClick = { /* TODO editar */ }) {
                    Icon(Icons.Default.Edit, "Editar", tint = Color.White)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Red)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Emerson A. F", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text("user@email.com", color = Color.White)
            Text("Administrador", color = Color.White, fontSize = 12.sp)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                readOnly = true
            )

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
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        ) {
            // Aquí va tu lista de productos más adelante
        }
    }
}
