package com.example.negolatina

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header con perfil (fondo rojo)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFFFF0000),
                        shape = RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 0.dp)
                    )
                    .padding(top = 40.dp, bottom = 24.dp, start = 24.dp, end = 24.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Foto de perfil circular
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Foto de perfil",
                            modifier = Modifier.size(80.dp),
                            tint = Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Nombre
                    Text(
                        text = "Emerson A. F",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Rol
                    Text(
                        text = "Cliente",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Menú de opciones
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp)
            ) {
                MenuOption(
                    icon = Icons.Default.Home,
                    text = "Inicio",
                    onClick = { navController.navigate("home") }
                )

                MenuOption(
                    icon = Icons.Default.Search,
                    text = "Buscar",
                    onClick = { navController.navigate("search") }
                )

                MenuOption(
                    icon = Icons.Default.Notifications,
                    text = "Notificaciones",
                    onClick = { navController.navigate("notifications") }
                )

                MenuOption(
                    icon = Icons.Default.ShoppingCart,
                    text = "Mis compras",
                    onClick = { navController.navigate("shopping_cart") }
                )

                MenuOption(
                    icon = Icons.Default.Favorite,
                    text = "Favoritos",
                    onClick = { /* Implementar favoritos */ }
                )

                MenuOption(
                    icon = Icons.Default.LocalOffer,
                    text = "Ofertas",
                    onClick = { navController.navigate("offers") }
                )

                MenuOption(
                    icon = Icons.Default.Person,
                    text = "Mi cuenta",
                    onClick = { /* Ya estamos aquí */ }
                )

                MenuOption(
                    icon = Icons.Default.Category,
                    text = "Categorías",
                    onClick = { navController.navigate("all_categories") }
                )

                MenuOption(
                    icon = Icons.Default.EmojiNature,
                    text = "Modo ecológico",
                    onClick = { navController.navigate("eco_mode") }
                )

                MenuOption(
                    icon = Icons.Default.ExitToApp,
                    text = "Cerrar sesión",
                    onClick = {
                        navController.navigate("welcome") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                MenuOption(
                    icon = Icons.Default.HelpOutline,
                    text = "Ayuda",
                    onClick = { navController.navigate("help") }
                )
            }
        }
    }
}

@Composable
fun MenuOption(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(24.dp),
            tint = Color(0xFF333333)
        )

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                color = Color(0xFF333333),
                fontWeight = FontWeight.Normal
            )
        )
    }
}