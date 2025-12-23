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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavController, productViewModel: ProductViewModel = viewModel()) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var discount by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }

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

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = category,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Categoría") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    allCategories.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption.name) },
                            onClick = {
                                category = selectionOption.name
                                expanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(value = discount, onValueChange = { discount = it }, label = { Text("Descuento (opcional)") }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val newProduct = Product(
                        id = UUID.randomUUID().toString(),
                        title = title,
                        description = description,
                        price = price,
                        rating = 0,
                        imageRes = R.drawable.logo_pollito,
                        category = category,
                        discount = discount.takeIf { d -> d.isNotBlank() },
                        imageUri = imageUri.takeIf { d -> d.isNotBlank() }
                    )
                    productViewModel.addProduct(newProduct)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Producto")
            }
        }
    }
}
