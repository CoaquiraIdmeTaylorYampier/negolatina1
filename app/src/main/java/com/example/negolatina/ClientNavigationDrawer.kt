package com.example.negolatina

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.negolatina.ui.theme.NegolatinaTheme


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
    val menuItems = listOf(
        DrawerMenuItem("home", "Inicio", Icons.Filled.Home),
        DrawerMenuItem("search", "Buscar", Icons.Filled.Search),
        DrawerMenuItem("notifications", "Notificaciones", Icons.Filled.Notifications),
        DrawerMenuItem("My purchases", "Mis compras", Icons.Filled.ShoppingCart),
        DrawerMenuItem("favorites", "Favoritos", Icons.Filled.Favorite),
        DrawerMenuItem("offers", "Ofertas", Icons.Filled.Star),
        DrawerMenuItem("client_account", "Mi cuenta", Icons.Filled.AccountCircle),
        DrawerMenuItem("categories", "Categorias", Icons.Filled.Category),
        DrawerMenuItem("all_products", "Productos", Icons.AutoMirrored.Filled.List),
        DrawerMenuItem("mode ecologic", "Modo ecologico", Icons.Filled.EnergySavingsLeaf),
        DrawerMenuItem("help", "Ayuda", Icons.Filled.Help), // ✅ CAMBIADO: "about_us" → "help" con ícono Help
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
            Image(
                painter = painterResource(id = profileViewModel.profileImageRes.value),
                contentDescription = "foto de perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
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