package com.example.negolatina

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.negolatina.ui.theme.NegolatinaTheme

@Preview(showBackground = true, name = "ProductDetail screen")
@Composable
fun ProductDetailScreen() {
    NegolatinaTheme {
       ProductDetailScreen(navController = rememberNavController(), productId="1")
    }
}
@Composable
fun ProductDetailScreen(navController: NavController, productId: String) {
    val product = sampleProducts.find { it.id == productId } ?: sampleProducts.first()
    val qty = remember { mutableStateOf("1") }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "product", modifier = Modifier.size(100.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(product.title)
                Text(product.price)
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text("Cantidad")
        OutlinedTextField(value = qty.value, onValueChange = { qty.value = it })
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { /*añadir a carrito */ }) {
            Text("Añadir al carrito")
        }
    }
}
