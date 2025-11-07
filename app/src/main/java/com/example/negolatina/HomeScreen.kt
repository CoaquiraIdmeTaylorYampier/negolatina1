package com.example.negolatina

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class Category(val title: String, val route: String, val imageRes: Int)

val categories = listOf(
    Category("Frutas y Verduras", "fruits_vegetables", R.drawable.uva),
    Category("Carnes y Embutidos", "meats_sausages", R.drawable.pollo),
    Category("Lácteos y Huevos", "dairy_eggs", R.drawable.queso),
    Category("Bebidas", "drinks", R.drawable.yogurt),
    Category("Snacks y Golosinas", "snacks", R.drawable.pringles),
    Category("Limpieza y Hogar", "cleaning_home", R.drawable.shampoo),
    Category("Granos y Abarrotes", "grains_groceries", R.drawable.arroz),
    Category("Panadería", "bakery", R.drawable.panintegral),
    Category("Carrito de Compras", "shopping_cart", R.drawable.power)
)

@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "logo",
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Negolatina - Categorías", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories) { category ->
                CategoryCard(category = category, navController = navController)
            }
        }
    }
}

@Composable
fun CategoryCard(category: Category, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { navController.navigate(category.route) },
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = category.imageRes),
                contentDescription = category.title,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = category.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}
