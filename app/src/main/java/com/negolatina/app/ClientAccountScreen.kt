package com.negolatina.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.negolatina.app.ui.theme.NegolatinaTheme

@Preview(showBackground = true, name = "Cuenta del Cliente")
@Composable
fun ClientAccountScreenPreview() {
    NegolatinaTheme {
        ClientAccountScreen(navController = rememberNavController(), profileViewModel = viewModel())
    }
}

@Composable
fun ClientAccountScreen(navController: NavController, profileViewModel: ProfileViewModel) {
    Column(Modifier.fillMaxSize()) {
        AccountAppBar(
            title = "Mi cuenta",
            onBackClicked = { navController.popBackStack() },
            onEditClicked = { navController.navigate("edit_profile") }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ClientProfileHeader(name = profileViewModel.name.value, imageRes = profileViewModel.profileImageRes.value)
            Spacer(modifier = Modifier.height(24.dp))
            ClientDataSection(profileViewModel)
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
private fun ClientProfileHeader(name: String, imageRes: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(name, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Cliente", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ClientDataSection(profileViewModel: ProfileViewModel) {
    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = Color.White.copy(alpha = 0.9f),
        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Black,

        focusedLabelColor = Color.White, // Color de la etiqueta cuando el campo está seleccionado (flotando)
        unfocusedLabelColor = Color.White.copy(alpha = 0.7f), // Color de la etiqueta cuando no está seleccionado
        disabledLabelColor = Color.White.copy(alpha = 0.7f), // Asegura que se vea bien en modo read-only
        
        cursorColor = Color.Red,
        focusedBorderColor = Color.White,
        unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
        disabledTextColor = Color.Black.copy(alpha = 0.8f)
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = profileViewModel.name.value,
            onValueChange = {},
            label = { Text("Nombre completo") },
            colors = textFieldColors,
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = profileViewModel.email.value,
            onValueChange = {},
            label = { Text("Correo electrónico") },
            colors = textFieldColors,
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )
        OutlinedTextField(
            value = profileViewModel.address.value,
            onValueChange = {},
            label = { Text("Dirección") },
            colors = textFieldColors,
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )
    }
}
