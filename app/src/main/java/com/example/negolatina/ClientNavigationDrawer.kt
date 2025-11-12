package com.example.negolatina

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ClientNavigationDrawer(
    navController: NavController,
    closeDrawer: () -> Unit
) {
    val menuItems = listOf(
        DrawerMenuItem("home", "Inicio", Icons.Filled.Home),
        DrawerMenuItem("all_products", "Productos", Icons.AutoMirrored.Filled.List),
        DrawerMenuItem("client_account", "Mi cuenta", Icons.Filled.AccountCircle),
        DrawerMenuItem("offers", "Ofertas", Icons.Filled.Star),
        DrawerMenuItem("about_us", "Acerca de", Icons.Filled.Info),
        DrawerMenuItem("welcome", "Cerrar sesión", Icons.AutoMirrored.Filled.ExitToApp)
    )

    Column(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "foto de perfil",
                modifier = Modifier.size(80.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Usuario Cliente",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            items(menuItems) { menuItem ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { 
                            closeDrawer()
                            navController.navigate(menuItem.route) {
                                popUpTo("home")
                                launchSingleTop = true
                            }
                        }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = menuItem.icon, contentDescription = menuItem.title)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(menuItem.title, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}
