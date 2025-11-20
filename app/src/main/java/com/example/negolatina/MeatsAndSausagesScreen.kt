package com.example.negolatina

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

/**
 * NOTA: Este archivo puede ser eliminado después de crear SharedComponents.kt
 * La funcionalidad será reemplazada por CategoryProductsScreen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeatsAndSausagesScreen(navController: NavController) {
    val meatProducts = allProducts.filter { it.category == "Carnes" }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carnes y Embutidos", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFE31E24))
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(meatProducts) { product ->
                // Ahora usa el ProductCard de SharedComponents.kt
                ProductCard(product = product, navController = navController)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MeatsAndSausagesScreenPreview() {
    val navController = rememberNavController()
    MeatsAndSausagesScreen(navController = navController)
}