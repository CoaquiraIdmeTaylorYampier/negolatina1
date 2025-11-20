package com.example.negolatina

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.negolatina.ui.theme.NegolatinaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavController, profileViewModel: ProfileViewModel) {
    var name by remember { mutableStateOf(profileViewModel.name.value) }
    var email by remember { mutableStateOf(profileViewModel.email.value) }
    var address by remember { mutableStateOf(profileViewModel.address.value) }

    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Editar Perfil", color = Color.White) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Atrás", tint = Color.White)
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
            EditableProfileHeader(profileViewModel, navController)
            Spacer(modifier = Modifier.height(24.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                val textFieldColors = OutlinedTextFieldDefaults.colors(
                    // Color del fondo del cuadro de texto cuando el usuario está escribiendo en él.
                    focusedContainerColor = Color.White,
                    // Color del fondo del cuadro de texto cuando no está seleccionado.
                    unfocusedContainerColor = Color.White,

                    // Color del texto que el usuario escribe.
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,

                    // Color de la etiqueta ("Nombre completo") cuando el campo está seleccionado.
                    focusedLabelColor = Color.White,
                    // Color de la etiqueta cuando el campo NO está seleccionado (cuando flota sobre el fondo rojo).
                    unfocusedLabelColor = Color.Black,

                    // Color del cursor parpadeante.
                    cursorColor = Color.Red,

                    // Color del borde cuando el campo está seleccionado.
                    focusedBorderColor = Color.White,
                    // Color del borde cuando el campo no está seleccionado.
                    unfocusedBorderColor = Color.White.copy(alpha = 0.7f)
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre completo") },
                    colors = textFieldColors,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Correo electrónico") },
                    colors = textFieldColors,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Dirección") },
                    colors = textFieldColors,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    profileViewModel.updateProfile(name, email, address)
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Guardar Cambios", color = Color.Red, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun EditableProfileHeader(profileViewModel: ProfileViewModel, navController: NavController) {
    Box {
        Image(
            painter = painterResource(id = profileViewModel.profileImageRes.value),
            contentDescription = "Foto de Perfil",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(Color.White, CircleShape)
                .clickable { navController.navigate("avatar_picker") }
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.PhotoCamera,
                contentDescription = "Cambiar foto",
                tint = Color.Red,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun EditProfileScreenPreview() {
    NegolatinaTheme {
        EditProfileScreen(navController = rememberNavController(),
            profileViewModel = viewModel())
    }
}
