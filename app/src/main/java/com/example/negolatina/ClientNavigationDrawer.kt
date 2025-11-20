package com.example.negolatina

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ClientNavigationDrawer(
    navController: NavController,
    closeDrawer: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        // Header con perfil (fondo rojo)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFF0000))
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
        DrawerMenuOption(
            icon = Icons.Default.Home,
            text = "Inicio",
            onClick = {
                closeDrawer()
                navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                }
            }
        )

        DrawerMenuOption(
            icon = Icons.Default.Search,
            text = "Buscar",
            onClick = {
                closeDrawer()
                navController.navigate("search")
            }
        )

        DrawerMenuOption(
            icon = Icons.Default.Notifications,
            text = "Notificaciones",
            onClick = {
                closeDrawer()
                navController.navigate("notifications")
            }
        )

        DrawerMenuOption(
            icon = Icons.Default.ShoppingCart,
            text = "Mis compras",
            onClick = {
                closeDrawer()
                navController.navigate("shopping_cart")
            }
        )

        DrawerMenuOption(
            icon = Icons.Default.Favorite,
            text = "Favoritos",
            onClick = {
                closeDrawer()
                // Implementar favoritos
            }
        )

        DrawerMenuOption(
            icon = Icons.Default.LocalOffer,
            text = "Ofertas",
            onClick = {
                closeDrawer()
                navController.navigate("offers")
            }
        )

        DrawerMenuOption(
            icon = Icons.Default.Person,
            text = "Mi cuenta",
            onClick = {
                closeDrawer()
                navController.navigate("client_account")
            }
        )

        DrawerMenuOption(
            icon = Icons.Default.Category,
            text = "Categorías",
            onClick = {
                closeDrawer()
                navController.navigate("all_categories")
            }
        )

        DrawerMenuOption(
            icon = Icons.Default.EmojiNature,
            text = "Modo ecológico",
            onClick = {
                closeDrawer()
                navController.navigate("eco_mode")
            }
        )

        DrawerMenuOption(
            icon = Icons.Default.ExitToApp,
            text = "Cerrar sesión",
            onClick = {
                closeDrawer()
                navController.navigate("welcome") {
                    popUpTo(0) { inclusive = true }
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

        DrawerMenuOption(
            icon = Icons.Default.HelpOutline,
            text = "Ayuda",
            onClick = {
                closeDrawer()
                navController.navigate("help")
            }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun DrawerMenuOption(
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