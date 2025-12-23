package com.example.negolatina

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductScreen(navController: NavController, productId: String, productViewModel: ProductViewModel = viewModel()) {
    val product = productViewModel.getProductById(productId)

    if (product == null) {
        // Muestra un mensaje o navega hacia atrás si el producto no se encuentra
        Text("Producto no encontrado")
        LaunchedEffect(Unit) {
            navController.popBackStack()
        }
        return
    }

    var title by remember { mutableStateOf(product.title) }
    var description by remember { mutableStateOf(product.description) }
    var price by remember { mutableStateOf(product.price) }
    var category by remember { mutableStateOf(product.category) }
    var discount by remember { mutableStateOf(product.discount ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Editar Producto") })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Título del producto") })
            OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Descripción") })
            OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Precio") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal))
            OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Categoría") })
            OutlinedTextField(value = discount, onValueChange = { discount = it }, label = { Text("Descuento (opcional)") })

            Button(onClick = {
                val updatedProduct = product.copy(
                    title = title,
                    description = description,
                    price = price,
                    category = category,
                    discount = discount.takeIf { d -> d.isNotBlank() }
                )
                productViewModel.updateProduct(updatedProduct)
                navController.popBackStack()
            }) {
                Text("Guardar Cambios")
            }
        }
    }
}
