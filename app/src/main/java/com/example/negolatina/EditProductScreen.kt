package com.example.negolatina

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductScreen(navController: NavController, productId: String, productViewModel: ProductViewModel = viewModel()) {
    val product = productViewModel.getProductById(productId)

    if (product == null) {
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
    var imageUri by remember { mutableStateOf(product.imageUri ?: "") }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                imageUri = uri.toString()
            }
        }
    )

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
            if (imageUri.isNotEmpty()) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = "Imagen seleccionada",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Button(
                onClick = {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (imageUri.isEmpty()) "Seleccionar Imagen de Galería" else "Cambiar Imagen")
            }

            OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Título del producto") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Descripción") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Precio") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Categoría") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = discount, onValueChange = { discount = it }, label = { Text("Descuento (opcional)") }, modifier = Modifier.fillMaxWidth())

            Button(onClick = {
                val updatedProduct = product.copy(
                    title = title,
                    description = description,
                    price = price,
                    category = category,
                    discount = discount.takeIf { d -> d.isNotBlank() },
                    imageUri = imageUri.takeIf { d -> d.isNotBlank() }
                )
                productViewModel.updateProduct(updatedProduct)
                navController.popBackStack()
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Guardar Cambios")
            }
        }
    }
}
