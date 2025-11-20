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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.negolatina.ui.theme.NegolatinaTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // <- CORRECCIÓN: usar el theme real del proyecto
            NegolatinaTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {
                    // Pantallas principales (ajusta según tengas implementadas)
                    composable("splash") { SplashScreen(navController) }
                    composable("welcome") { WelcomeScreen(navController) }
                    composable("login") { LoginScreen(navController) }
                    composable("register") { RegisterScreen(navController) }
                    composable("onboarding_sell") { OnboardingSellScreen(navController) }
                    composable("onboarding_buy") { OnboardingBuyScreen(navController) }
                    composable("home") { HomeScreen(navController) }
                    composable("product/{productId") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("productId") ?: "0"
                        ProductDetailScreen(navController, id)
                    }

                    // Categorías / pantallas que mostraste antes
                    composable("snacks") { SnacksScreen(navController) }
                    composable("apple_product") { AppleProductScreen(navController) } // si AppleProductScreen no recibe navController, cambia aquí
                    composable("drinks") { DrinksScreen(navController) }
                    composable("fruits_vegetables") { FruitsAndVegetablesScreen(navController) }
                    composable("dairy_eggs") { DairyAndEggsScreen(navController) }
                    composable("meats_sausages") { MeatsAndSausagesScreen(navController) }
                    composable("shopping_cart") { ShoppingCartScreen(navController) }
                    composable("cleaning_home") { CleaningAndHomeScreen(navController) }
                    composable("grains_groceries") { GrainsAndGroceriesScreen(navController) }
                    composable("bakery") { BakeryScreen(navController) }
                }
            }
        }
    }
}

// SplashScreen fuera de la clase
@Composable
fun SplashScreen(navController: NavController) {
    var start by remember { mutableStateOf(false) }
    val alpha = animateFloatAsState(if (start) 1f else 0f)
    LaunchedEffect(Unit) {
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
                modifier = Modifier.size(120.dp).alpha(alpha.value)
            )
            Spacer(Modifier.height(12.dp))
            Text("Negolatina", style = MaterialTheme.typography.headlineSmall)
        }
    }
}
