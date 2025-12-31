package com.negolatina.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.negolatina.app.ui.theme.NegolatinaTheme

@Preview(showBackground = true, name = "Client Navigation Preview")
@Composable
fun ClientNavigationDrawerPreview() {
    NegolatinaTheme {
        ClientNavigationDrawer(navController = rememberNavController(), closeDrawer = {}, profileViewModel = viewModel())
    }
}

@Composable
fun ClientNavigationDrawer(
    navController: NavController,
    closeDrawer: () -> Unit,
    profileViewModel: ProfileViewModel
) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    if (showLogoutDialog) {
        LogoutConfirmationDialog(
            onConfirm = {
                showLogoutDialog = false
                closeDrawer()
                navController.navigate("welcome") {
                    popUpTo("home") { inclusive = true }
                }
            },
            onDismiss = { showLogoutDialog = false }
        )
    }

    val menuItems = listOf(
        DrawerMenuItem("home", "Inicio", Icons.Filled.Home),
        DrawerMenuItem("notifications", "Notificaciones", Icons.Filled.Notifications),
        DrawerMenuItem("my_orders", "Mis compras", Icons.Filled.ShoppingBasket),
        DrawerMenuItem("favorites", "Favoritos", Icons.Filled.Favorite),
        DrawerMenuItem("client_offers", "Ofertas", Icons.Filled.Star),
        DrawerMenuItem("client_account", "Mi cuenta", Icons.Filled.AccountCircle),
        DrawerMenuItem("all_categories", "Categorias", Icons.Filled.Category),
        DrawerMenuItem("all_products", "Productos", Icons.AutoMirrored.Filled.List),
        DrawerMenuItem("eco_mode", "Modo ecologico", Icons.Filled.EnergySavingsLeaf),
        DrawerMenuItem("help", "Ayuda", Icons.Filled.Help),
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = profileViewModel.profileImageRes.value,
                contentDescription = "foto de perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = profileViewModel.name.value,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "Cliente",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f).background(MaterialTheme.colorScheme.surface)
        ) {
            items(menuItems) { menuItem ->
                DrawerItemRow(menuItem = menuItem, onClick = {
                    closeDrawer()
                    navController.navigate(menuItem.route) { launchSingleTop = true }
                })
            }
            item {
                 HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            }
            item {
                DrawerItemRow(
                    menuItem = DrawerMenuItem("logout", "Cerrar sesión", Icons.AutoMirrored.Filled.ExitToApp),
                    onClick = { showLogoutDialog = true }
                )
            }
        }
    }
}

@Composable
private fun DrawerItemRow(menuItem: DrawerMenuItem, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = menuItem.icon, contentDescription = menuItem.title)
        Spacer(modifier = Modifier.width(16.dp))
        Text(menuItem.title, style = MaterialTheme.typography.bodyLarge)
    }
}
