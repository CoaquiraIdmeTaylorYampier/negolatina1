package com.negolatina.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.negolatina.app.ui.theme.NegolatinaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val settingsViewModel: SettingsViewModel = viewModel()
            val productViewModel: ProductViewModel = viewModel()
            val orderViewModel: OrderViewModel = viewModel()
            val isEcoMode by settingsViewModel.isEcoModeEnabled

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

                    composable("home") { HomeScreen(navController, profileViewModel, productViewModel) }
                    composable("client_account") { ClientAccountScreen(navController, profileViewModel) }
                    composable("edit_profile") { EditProfileScreen(navController, profileViewModel) }
                    composable("avatar_picker") { AvatarPickerScreen(navController, profileViewModel) }

                    // Detalle
                    composable(
                        "product/{productId}",
                        arguments = listOf(navArgument("productId") { type = NavType.StringType })
                    ) { entry ->
                        entry.arguments?.getString("productId")?.let {
                            ProductDetailScreen(navController, it, productViewModel)
                        }
                    }

                    // Categorías
                    composable("snacks") {
                        CategoryProductsScreen(navController, "Snacks", "Snacks y Golosinas", productViewModel)
                    }

                    composable("drinks") {
                        CategoryProductsScreen(navController, "Bebidas", "Bebidas", productViewModel)
                    }

                    composable("fruits_vegetables") {
                        CategoryProductsScreen(navController, "Frutas y Verduras", "Frutas y Verduras", productViewModel)
                    }

                    composable("dairy_eggs") {
                        CategoryProductsScreen(navController, "Lácteos", "Lácteos y Huevos", productViewModel)
                    }

                    composable("meats_sausages") {
                        CategoryProductsScreen(navController, "Carnes", "Carnes y Embutidos", productViewModel)
                    }

                    composable("cleaning_home") {
                        CategoryProductsScreen(navController, "Limpieza", "Limpieza y Hogar", productViewModel)
                    }

                    composable("grains_groceries") {
                        CategoryProductsScreen(navController, "Abarrotes", "Granos y Abarrotes", productViewModel)
                    }

                    composable("bakery") {
                        CategoryProductsScreen(navController, "Panadería", "Panadería", productViewModel)
                    }
                    composable("Local_Emprend"){
                        CategoryProductsScreen(navController,"Emprendedores Locales","Emprendedores Locales", productViewModel)
                    }

                    // Especiales
                    composable("apple_product") { AppleProductScreen(navController) }
                    composable("shopping_cart") { ImprovedShoppingCartScreen(navController) }

                    // Flujo de compra
                    composable("checkout") { CheckoutScreen(navController) }
                    composable(
                        "payment_card/{address}",
                        arguments = listOf(navArgument("address") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val address = backStackEntry.arguments?.getString("address") ?: ""
                        CardPaymentScreen(navController, address, productViewModel)
                    }

                    composable(
                        "payment_yape/{address}",
                        arguments = listOf(navArgument("address") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val address = backStackEntry.arguments?.getString("address") ?: ""
                        YapePaymentScreen(navController, address, productViewModel)
                    }

                    composable(
                        "payment_plin/{address}",
                        arguments = listOf(navArgument("address") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val address = backStackEntry.arguments?.getString("address") ?: ""
                        PlinPaymentScreen(navController, address, productViewModel)
                    }

                    composable(
                        "payment_cash/{address}",
                        arguments = listOf(navArgument("address") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val address = backStackEntry.arguments?.getString("address") ?: ""
                        CashOnDeliveryScreen(navController, address, productViewModel)
                    }
                    composable("purchase_success") { PurchaseSuccessScreen(navController) }
                    composable(
                        "invoice/{orderId}",
                        arguments = listOf(navArgument("orderId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
                        InvoiceScreen(navController, orderId)
                    }

                    // Listados
                    composable("all_categories") { AllCategoriesScreen(navController) }
                    composable("all_products") { AllProductsScreen(navController, productViewModel) }
                    composable("my_orders") { MyOrdersScreen(navController, orderViewModel) } // Nueva ruta
                    composable("inventory_categories") { InventoryCategoriesScreen(navController) }
                    composable(
                        "inventory_product_list/{categoryName}",
                        arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
                        val products by productViewModel.products.collectAsState()
                        val filteredProducts = products.filter { it.category == categoryName }
                        InventoryProductListScreen(
                            navController = navController,
                            categoryName = categoryName,
                            products = filteredProducts,
                            onDeleteProduct = { productId ->
                                productViewModel.deleteProduct(productId)
                            }
                        )
                    }
                    
                    composable("discount_products") { DiscountProductsScreen(navController, productViewModel) }
                    composable("client_offers") { ClientOffersScreen(navController, productViewModel) } // Nueva ruta para cliente

                    // Usuario y Admin
                    composable("admin_dashboard") { AdminDashboardScreen(navController, profileViewModel, productViewModel) }
                    composable("admin_orders") { AdminOrdersScreen(navController, orderViewModel) }
                    composable("admin_account") {
                        val products by productViewModel.products.collectAsState()
                        AdminAccountScreen(
                            navController = navController,
                            products = products,
                            onDeleteProduct = { productId ->
                                productViewModel.deleteProduct(productId)
                            }
                        )
                    }
                    composable("add_product") {
                        AddProductScreen(navController = navController, onAddProduct = {
                            productViewModel.addProduct(it)
                        })
                    }
                    composable(
                        "edit_product/{productId}",
                        arguments = listOf(navArgument("productId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val productId = backStackEntry.arguments?.getString("productId")
                        val product = productId?.let { productViewModel.getProductById(it) }
                        EditProductScreen(
                            navController = navController,
                            product = product,
                            onUpdateProduct = {
                                productViewModel.updateProduct(it)
                            }
                        )
                    }

                    // Extras
                    composable("search") { SearchScreen(navController) }
                    composable("notifications") { NotificationsScreen(navController, orderViewModel) }
                    composable("offers") { OffersScreen(navController) } // Placeholder original, se mantiene por si acaso
                    composable("sell") { SellScreen(navController) }

                    composable("eco_mode") { 
                        EcoModeScreen(navController = navController) 
                    }
                    
                    composable("help") { HelpScreen(navController) }
                    composable("favorites") { FavoritesScreen(navController) }
                    composable("admin_navigation") { AdminDashboardScreen(navController, profileViewModel, productViewModel) }
                   }
            }
        }
    }
}

// Placeholder
@Composable fun SearchScreen(navController: NavController) { CenterText("Pantalla de Búsqueda - En desarrollo") }
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
