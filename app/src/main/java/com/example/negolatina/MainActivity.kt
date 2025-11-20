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

                    composable("splash") { SplashScreen(navController) }
                    composable("welcome") { WelcomeScreen(navController) }

                    // Autenticación
                    composable("login") { LoginScreen(navController) }
                    composable("register") { RegisterScreen(navController) }

                    // Onboarding
                    composable("onboarding_sell") { OnboardingSellScreen(navController) }
                    composable("onboarding_buy") { OnboardingBuyScreen(navController) }

                    // Inicio
                    composable("home") { HomeScreen(navController) }

                    // Detalle
                    composable(
                        "product/{productId}",
                        arguments = listOf(navArgument("productId") { type = NavType.StringType })
                    ) { entry ->
                        val productId = entry.arguments?.getString("productId") ?: "c1"
                        ProductDetailScreen(navController, productId)
                    }

                    // Categorías
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
                        GrainsAndGroceriesScreen(navController)
                    }

                    composable("bakery") {
                        CategoryProductsScreen(navController, "Panadería", "Panadería")
                    }

                    // Especiales
                    composable("apple_product") { AppleProductScreen(navController) }
                    composable("shopping_cart") { ImprovedShoppingCartScreen(navController) }

                    // Flujo de compra
                    composable("checkout") { CheckoutScreen(navController) }
                    composable("purchase_success") { PurchaseSuccessScreen(navController) }

                    // Listados
                    composable("all_categories") { AllCategoriesScreen(navController) }
                    composable("all_products") { AllProductsScreen(navController) }

                    // Usuario - ACTUALIZADO
                    composable("admin_account") { AdminAccountScreen(navController) }
                    composable("client_account") { ClientAccountScreen(navController) }

                    // Extras
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

// --- SplashScreen ---
@Composable
fun SplashScreen(navController: NavController) {

    var start by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        label = "splash_alpha"
    )

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

// --- Placeholder screens ---
@Composable fun SearchScreen(navController: NavController) { CenterText("Pantalla de Búsqueda - En desarrollo") }
@Composable fun NotificationsScreen(navController: NavController) { CenterText("Notificaciones - En desarrollo") }
@Composable fun OffersScreen(navController: NavController) { CenterText("Ofertas - En desarrollo") }
@Composable fun SellScreen(navController: NavController) { CenterText("Vender Producto - En desarrollo") }
@Composable fun EcoModeScreen(navController: NavController) { CenterText("Modo Ecológico - En desarrollo") }
@Composable fun AboutUsScreen(navController: NavController) { CenterText("Acerca de Nosotros - En desarrollo") }

@Composable
fun CenterText(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text)
    }
}