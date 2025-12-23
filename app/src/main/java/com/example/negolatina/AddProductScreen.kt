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
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavController, productViewModel: ProductViewModel = viewModel()) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var discount by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Añadir Nuevo Producto") })
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
                val newProduct = Product(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    description = description,
                    price = price,
                    rating = 0,
                    imageRes = R.drawable.logo_pollito, // Imagen por defecto
                    category = category,
                    discount = discount.takeIf { d -> d.isNotBlank() }
                )
                productViewModel.addProduct(newProduct)
                navController.popBackStack()
            }) {
                Text("Guardar Producto")
            }
        }
    }
}
