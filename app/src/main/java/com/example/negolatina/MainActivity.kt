package com.example.negolatina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.negolatina.app.ui.theme.NegolatinaTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NegolatinaTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") { SplashScreen(navController) }
                    composable("welcome") { WelcomeScreen(navController) }
                    composable("login") { LoginScreen(navController) }
                    composable("register") { RegisterScreen(navController) }
                    composable("onboarding_sell") { OnboardingSellScreen(navController) }
                    composable("onboarding_buy") { OnboardingBuyScreen(navController) }
                    composable("home") { HomeScreen(navController) }
                    composable("product/{productId}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("productId") ?: "0"
                        ProductDetailScreen(navController, id)
                    }
                }
            }
        }
    }
}

@Composable
fun SplashScreen(navController: androidx.navigation.NavController) {
    // Simple fade-in logo splash, then navigate to Welcome.
    var start by remember { mutableStateOf(false) }
    val alpha = animateFloatAsState(if (start) 1f else 0f)
    LaunchedEffect(Unit) {
        start = true
        delay(1400)
        navController.navigate("welcome") {
            popUpTo("splash") { inclusive = true }
        }
    }
    androidx.compose.material.Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.ic_negolatina_logo), contentDescription = "logo", modifier = Modifier.size(120.dp).alpha(alpha.value))
            Spacer(Modifier.height(12.dp))
            Text("Negolatina", style = MaterialTheme.typography)
        }
    }
}
