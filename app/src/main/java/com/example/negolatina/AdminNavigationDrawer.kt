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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.negolatina.ui.theme.NegolatinaTheme

@Preview(showBackground = true, name = "Menú del Admin")
@Composable
fun AdminNavigationDrawerPreview() {
    NegolatinaTheme {
        val navController = rememberNavController()
        AdminNavigationDrawer(navController = navController, closeDrawer = {})
    }
}

@Composable
fun AdminNavigationDrawer(
    navController: NavController,
    closeDrawer: () -> Unit
) {
    val menuItems = listOf(
        DrawerMenuItem(route = "home", title = "Inicio", icon = Icons.Filled.Home),
        DrawerMenuItem(route = "search", title = "Buscar", icon = Icons.Filled.Search),
        DrawerMenuItem(route = "notifications", title = "Notificaciones", icon = Icons.Filled.Notifications),
        DrawerMenuItem(route = "offers", title = "Ofertas", icon = Icons.Filled.Star),
        DrawerMenuItem(route = "sell", title = "Vender", icon = Icons.Default.Add),
        DrawerMenuItem(route = "admin_account", title = "Mi cuenta", icon = Icons.Filled.AccountCircle),
        DrawerMenuItem(route = "all_categories", title = "Categorias", icon = Icons.AutoMirrored.Filled.List),
        DrawerMenuItem(route = "eco_mode", title = "🌿 Modo ecológico", icon = Icons.Filled.EnergySavingsLeaf), 
        DrawerMenuItem(route = "welcome", title = "Cerrar sesión", icon = Icons.AutoMirrored.Filled.ExitToApp)
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
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "foto de perfil",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Jesus V. C.", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text("Administrador", color = Color.White, fontSize = 14.sp)
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
                            navController.navigate(menuItem.route)
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .clickable { 
                    closeDrawer()
                    navController.navigate("help")
                }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Ayuda", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
