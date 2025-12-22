package com.example.negolatina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
            // Instanciamos el ViewModel de configuración aquí para que el estado sea global
            val settingsViewModel: SettingsViewModel = viewModel()
            val isEcoMode by settingsViewModel.isEcoModeEnabled
            
            // Si el modo eco está activo, forzamos el tema oscuro (o la lógica que prefieras)
            val useDarkTheme = isEcoMode || isSystemInDarkTheme()

            NegolatinaTheme(darkTheme = useDarkTheme) {

                val navController = rememberNavController()
                val profileViewModel: ProfileViewModel = viewModel()

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

                    // CONECTAMOS EL VIEWMODEL A LAS PANTALLAS
                    composable("home") { HomeScreen(navController, profileViewModel) }
                    composable("client_account") { ClientAccountScreen(navController, profileViewModel) }
                    composable("edit_profile") { EditProfileScreen(navController, profileViewModel) }
                    composable("avatar_picker") { AvatarPickerScreen(navController, profileViewModel) }

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
                        CategoryProductsScreen(navController, "Abarrotes", "Granos y Abarrotes")
                    }

                    composable("bakery") {
                        CategoryProductsScreen(navController, "Panadería", "Panadería")
                    }
                    composable("Local_Emprend"){
                        CategoryProductsScreen(navController,"Emprendedores Locales","Emprendedores Locales")
                    }

                    // Especiales
                    composable("apple_product") { AppleProductScreen(navController) }
                    composable("shopping_cart") { ImprovedShoppingCartScreen(navController) }

                    // Flujo de compra
                    composable("checkout") { CheckoutScreen(navController) }
                    // AGREGA ESTAS 4 RUTAS NUEVAS:
                    composable(
                        "payment_card/{address}",
                        arguments = listOf(navArgument("address") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val address = backStackEntry.arguments?.getString("address") ?: ""
                        CardPaymentScreen(navController, address)
                    }

                    composable(
                        "payment_yape/{address}",
                        arguments = listOf(navArgument("address") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val address = backStackEntry.arguments?.getString("address") ?: ""
                        YapePaymentScreen(navController, address)
                    }

                    composable(
                        "payment_plin/{address}",
                        arguments = listOf(navArgument("address") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val address = backStackEntry.arguments?.getString("address") ?: ""
                        PlinPaymentScreen(navController, address)
                    }

                    composable(
                        "payment_cash/{address}",
                        arguments = listOf(navArgument("address") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val address = backStackEntry.arguments?.getString("address") ?: ""
                        CashOnDeliveryScreen(navController, address)
                    }
                    composable("purchase_success") { PurchaseSuccessScreen(navController) }

                    // Listados
                    composable("all_categories") { AllCategoriesScreen(navController) }
                    composable("all_products") { AllProductsScreen(navController) }

                    // Usuario
                    composable("admin_account") { AdminAccountScreen(navController) }

                    // Extras
                    composable("search") { SearchScreen(navController) }
                    composable("notifications") { NotificationsScreen(navController) }
                    composable("offers") { OffersScreen(navController) }
                    composable("sell") { SellScreen(navController) }
                    
                    // Modificamos cómo se llama a EcoModeScreen
                    composable("eco_mode") { 
                        EcoModeScreen(navController = navController) 
                    }
                    
                    composable("help") { HelpScreen(navController) }
                    composable("favorites") { FavoritesScreen(navController) }
                   }
            }
        }
    }
}

//  SplashScreen Restaurada
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
            .background(Color(0xFFE31E24)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_pollo),
            contentDescription = "logo",
            modifier = Modifier
                .size(400.dp)
                .alpha(alpha)
        )
    }
}

// Placeholder
@Composable fun SearchScreen(navController: NavController) { CenterText("Pantalla de Búsqueda - En desarrollo") }
@Composable fun NotificationsScreen(navController: NavController) { CenterText("Notificaciones - En desarrollo") }
@Composable fun OffersScreen(navController: NavController) { CenterText("Ofertas - En desarrollo") }
@Composable fun SellScreen(navController: NavController) { CenterText("Vender Producto - En desarrollo") }
@Composable
fun CenterText(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text)
    }
}
