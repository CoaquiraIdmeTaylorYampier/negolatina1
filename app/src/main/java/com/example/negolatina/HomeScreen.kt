package com.example.negolatina

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.
Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

data class Product(val id: String, val title: String, val price: String)

val sampleProducts = listOf(
    Product("1", "Carne de Res", "S/.16.90 x Kg"),
    Product("2", "Smirnoff", "S/.25.40"),
    Product("3", "Avena tres osito", "S/.4.50")
)

@Composable
fun HomeScreen(navController: androidx.navigation.NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        // simple top bar area
        Row(modifier = Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.ic_negolatina_logo), contentDescription = "logo", modifier = Modifier.size(48.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Buscar" )
        }
        LazyColumn(modifier = Modifier.fillMaxSize().padding(12.dp)) {
            items(sampleProducts) { p ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp).clickable { navController.navigate("product/${p.id}") }) {
                    Row(modifier = Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Image(painter = painterResource(id = R.drawable.ic_negolatina_logo), contentDescription = "product", modifier = Modifier.size(64.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(p.title)
                            Text(p.price)
                        }
                    }
                }
            }
        }
    }
}
