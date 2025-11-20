package com.example.negolatina

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

/**
 * Pantalla unificada para mostrar productos por categoría
 *
 * Esta pantalla reemplaza las siguientes pantallas individuales:
 * - DrinksScreen (Bebidas)
 * - FruitsAndVegetablesScreen (Frutas y Verduras)
 * - DairyAndEggsScreen (Lácteos y Huevos)
 * - MeatsAndSausagesScreen (Carnes y Embutidos)
 * - CleaningAndHomeScreen (Limpieza y Hogar)
 * - BakeryScreen (Panadería)
 * - SnacksScreen (Snacks y Golosinas)
 *
 * @param navController Controlador de navegación
 * @param categoryName Nombre de la categoría a filtrar (debe coincidir con Product.category)
 * @param displayTitle Título a mostrar en la barra superior
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryProductsScreen(
    navController: NavController,
    categoryName: String,
    displayTitle: String = categoryName
) {
    // Filtrar productos por categoría (ignora mayúsculas/minúsculas)
    val productosCategoria = allProducts.filter {
        it.category.equals(categoryName, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = displayTitle,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver atrás",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFE31E24)
                )
            )
        }
    ) { paddingValues ->
        if (productosCategoria.isEmpty()) {
            // Mostrar mensaje cuando no hay productos en la categoría
            MensajeEstadoVacio()
        } else {
            // Mostrar grid de productos
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(productosCategoria) { producto ->
                    ProductCard(product = producto, navController = navController)
                }
            }
        }
    }
}

/**
 * Componente que muestra un mensaje cuando no hay productos disponibles
 */
@Composable
private fun MensajeEstadoVacio() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "No hay productos disponibles",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Pronto agregaremos más productos",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.LightGray
            )
        }
    }
}

// ========== PREVIEWS ==========

@Preview(showSystemUi = true, name = "Categoría con productos")
@Composable
fun CategoryProductsScreenPreview() {
    val navController = rememberNavController()
    CategoryProductsScreen(
        navController = navController,
        categoryName = "Bebidas",
        displayTitle = "Bebidas"
    )
}

@Preview(showSystemUi = true, name = "Categoría vacía")
@Composable
fun CategoryProductsScreenEmptyPreview() {
    val navController = rememberNavController()
    CategoryProductsScreen(
        navController = navController,
        categoryName = "Categoría Inexistente",
        displayTitle = "Sin Productos"
    )
}

@Preview(showBackground = true, name = "Mensaje vacío")
@Composable
fun MensajeEstadoVacioPreview() {
    MensajeEstadoVacio()
}