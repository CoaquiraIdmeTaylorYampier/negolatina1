package com.negolatina.app

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.negolatina.app.ui.theme.NegolatinaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductScreen(
    navController: NavController, 
    product: Product?,
    onUpdateProduct: (Product) -> Unit
) {
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
    var cantidad by remember { mutableStateOf(product.cantidad.toString()) } 
    var imageUri by remember { mutableStateOf(product.imageUri ?: "") }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                imageUri = uri.toString()
            }
        }
    )

    val brandRed = Color(0xFFFF0000)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Producto", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = brandRed
                )
            )
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
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = brandRed.copy(alpha = 0.8f))
            ) {
                Text(if (imageUri.isEmpty()) "Seleccionar Imagen de Galería" else "Cambiar Imagen")
            }

            OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Título del producto") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Descripción") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Precio") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Categoría") }, modifier = Modifier.fillMaxWidth())
            
            OutlinedTextField(
                value = cantidad,
                onValueChange = { cantidad = it },
                label = { Text("Cantidad en Stock") }, 
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(value = discount, onValueChange = { discount = it }, label = { Text("Descuento (opcional)") }, modifier = Modifier.fillMaxWidth())

            Button(
                onClick = {
                    val updatedProduct = product.copy(
                        title = title,
                        description = description,
                        price = price,
                        category = category,
                        discount = discount.takeIf { d -> d.isNotBlank() },
                        imageUri = imageUri.takeIf { d -> d.isNotBlank() },
                        cantidad = cantidad.toIntOrNull() ?: 0
                    )
                    onUpdateProduct(updatedProduct)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = brandRed)
            ) {
                Text("Guardar Cambios")
            }
        }
    }
}

@Preview(showBackground = true, name = "Edit Product Screen")
@Composable
fun EditProductScreenPreview() {
    NegolatinaTheme {
        val fakeProduct = Product(
            id = "preview1",
            title = "Producto de Muestra",
            description = "Esta es una descripción de ejemplo para el producto que se está editando.",
            price = "S/ 19.99",
            rating = 4,
            imageRes = R.drawable.logo_pollito,
            category = "Abarrotes",
            discount = "10%",
            imageUri = null,
            cantidad = 25
        )
        EditProductScreen(
            navController = rememberNavController(),
            product = fakeProduct,
            onUpdateProduct = {  }
        )
    }
}
