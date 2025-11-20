package com.example.negolatina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.negolatina.ui.theme.NegolatinaTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NegolatinaTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {
                    // Pantalla de inicio
                    composable("splash") { SplashScreen(navController) }
                    composable("welcome") { WelcomeScreen(navController) }

                    // Autenticación
                    composable("login") { LoginScreen(navController) }
                    composable("register") { RegisterScreen(navController) }

                    // Onboarding
                    composable("onboarding_sell") { OnboardingSellScreen(navController) }
                    composable("onboarding_buy") { OnboardingBuyScreen(navController) }

                    // Pantalla principal
                    composable("home") { HomeScreen(navController) }

                    // Detalles de producto
                    composable(
                        route = "product/{productId}",
                        arguments = listOf(navArgument("productId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val productId = backStackEntry.arguments?.getString("productId") ?: "c1"
                        ProductDetailScreen(navController, productId)
                    }

                    // Categorías - Usando pantalla unificada
                    composable("snacks") {
                        CategoryProductsScreen(navController, "Snacks", "Snacks y Golosinas")
                    }
                    composable("drinks") {
                        CategoryProductsScreen(navController, "Bebidas", "Bebidas")
                    }
                    composable("fruits_vegetables") {
                        CategoryProductsScreen(navController, "Frutas y Verduras", "Frutas y Verduras")
                    }
                    composable("dairy_eggs") {
                        CategoryProductsScreen(navController, "Lácteos", "Lácteos y Huevos")
                    }
                    composable("meats_sausages") {
                        CategoryProductsScreen(navController, "Carnes", "Carnes y Embutidos")
                    }
                    composable("cleaning_home") {
                        CategoryProductsScreen(navController, "Limpieza", "Limpieza y Hogar")
                    }
                    composable("grains_groceries") {
                        GrainsAndGroceriesScreen(navController) // Esta tiene diseño especial
                    }
                    composable("bakery") {
                        CategoryProductsScreen(navController, "Panadería", "Panadería")
                    }

                    // Pantallas especiales
                    composable("apple_product") { AppleProductScreen(navController) }
                    composable("shopping_cart") { ShoppingCartScreen(navController) }

                    // Listados completos
                    composable("all_categories") { AllCategoriesScreen(navController) }
                    composable("all_products") { AllProductsScreen(navController) }

                    // Cuentas de usuario
                    composable("admin_account") { AdminAccountScreen(navController) }
                    composable("client_account") { ClientAccountScreen(navController) }

                    // Otras rutas
                    composable("search") { SearchScreen(navController) }
                    composable("notifications") { NotificationsScreen(navController) }
                    composable("offers") { OffersScreen(navController) }
                    composable("sell") { SellScreen(navController) }
                    composable("eco_mode") { EcoModeScreen(navController) }
                    composable("help") { HelpScreen(navController) }
                    composable("about_us") { AboutUsScreen(navController) }
                }
            }
        }
    }
}

// SplashScreen mejorado
@Composable
fun SplashScreen(navController: NavController) {
    var start by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(targetValue = if (start) 1f else 0f, label = "splash_alpha")

    LaunchedEffect(Unit) {
        start = true
        delay(1400)
        navController.navigate("welcome") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "logo",
                modifier = Modifier
                    .size(120.dp)
                    .alpha(alpha)
            )
            Spacer(Modifier.height(12.dp))
            Text("Negolatina", style = MaterialTheme.typography.headlineSmall)
        }
    }
}

// Pantallas placeholder para rutas faltantes
@Composable
fun SearchScreen(navController: NavController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Pantalla de Búsqueda - En desarrollo")
    }
}

@Composable
fun NotificationsScreen(navController: NavController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Notificaciones - En desarrollo")
    }
}

@Composable
fun OffersScreen(navController: NavController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Ofertas - En desarrollo")
    }
}

@Composable
fun SellScreen(navController: NavController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Vender Producto - En desarrollo")
    }
}

@Composable
fun EcoModeScreen(navController: NavController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Modo Ecológico - En desarrollo")
    }
}

@Composable
fun HelpScreen(navController: NavController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Ayuda - En desarrollo")
    }
}

@Composable
fun AboutUsScreen(navController: NavController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Acerca de Nosotros - En desarrollo")
    }
}