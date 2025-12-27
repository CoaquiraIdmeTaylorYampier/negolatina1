package com.negolatina.app

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.negolatina.app.ui.theme.NegolatinaTheme

private val BrandRed = Color(0xFFFF0000)
private val GrayBackground = Color(0xFFF8F9FA)
private val GreenStock = Color(0xFF2E7D32)
private val RedNoStock = Color(0xFFC62828)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryProductListScreen(
    navController: NavController,
    categoryName: String,
    products: List<Product>,
    onDeleteProduct: (String) -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    var productToDelete by remember { mutableStateOf<Product?>(null) }

    if (showDeleteDialog && productToDelete != null) {
        DeleteConfirmationDialog(
            productName = productToDelete!!.title,
            onConfirm = {
                onDeleteProduct(productToDelete!!.id)
                showDeleteDialog = false
                productToDelete = null
            },
            onDismiss = {
                showDeleteDialog = false
                productToDelete = null
            }
        )
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.background(Color.White)
            ) {
                CenterAlignedTopAppBar(
                    title = { Text(categoryName, fontWeight = FontWeight.Bold, color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Atrás",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = BrandRed)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier.weight(1f).height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFF5F5F5),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Color.Gray)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Buscar en $categoryName...", color = Color.Gray, fontSize = 14.sp)
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Surface(
                        modifier = Modifier.size(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
                        color = Color.White
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(imageVector = Icons.Default.FilterList, contentDescription = "Filtro", tint = Color.Black)
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { navController.navigate("add_product") },
                containerColor = BrandRed,
                contentColor = Color.White,
                shape = RoundedCornerShape(50),
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Añadir nuevo", fontWeight = FontWeight.Bold)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        containerColor = GrayBackground
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(
                text = "${products.size} productos",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            )
            LazyColumn(
                contentPadding = PaddingValues(bottom = 100.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                items(products) { product ->
                    ProductRowCard(product = product, onEdit = {
                        navController.navigate("edit_product/${product.id}")
                    }, onDelete = {
                        productToDelete = product
                        showDeleteDialog = true
                    })
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun DeleteConfirmationDialog(
    productName: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Confirmar Eliminación") },
        text = { Text("¿Estás seguro de que deseas eliminar el producto \"$productName\"?") },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = BrandRed)
            ) {
                Text("Sí")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("No")
            }
        }
    )
}

@Composable
fun ProductRowCard(
    product: Product,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(0.5.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (product.imageUri != null) {
                AsyncImage(
                    model = product.imageUri,
                    contentDescription = product.title,
                    modifier = Modifier.size(64.dp).clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.title,
                    modifier = Modifier.size(64.dp).clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = product.price,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                StockStatusChip(quantity = product.cantidad)
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.height(50.dp)
            ) {
                Icon(imageVector = Icons.Outlined.Edit, contentDescription = "Editar", tint = Color.Gray, modifier = Modifier.size(24.dp).clickable(onClick = onEdit))
                Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Borrar", tint = Color.Gray, modifier = Modifier.size(24.dp).clickable(onClick = onDelete))
            }
        }
    }
}

@Composable
fun StockStatusChip(quantity: Int) {
    val isInStock = quantity > 0
    val backgroundColor = if (isInStock) GreenStock.copy(alpha = 0.1f) else RedNoStock.copy(alpha = 0.1f)
    val textColor = if (isInStock) GreenStock else RedNoStock
    val text = if (isInStock) "En Stock ($quantity)" else "Agotado"

    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Preview(showBackground = true, name = "Inventory Product List")
@Composable
fun InventoryProductListScreenPreview() {
    val fakeProducts = listOf(
        Product(id = "p1", title = "Plátanos Frescos", description = "", price = "S/ 1.29 / unidad", rating = 4, imageRes = R.drawable.frutverd_platano, category = "Frutas y Verduras", cantidad = 50),
        Product(id = "p2", title = "Leche Entera Gloria", description = "", price = "S/ 2.49 / litro", rating = 5, imageRes = R.drawable.lacteos_leche, category = "Lácteos", cantidad = 0),
        Product(id = "p3", title = "Pechuga de Pollo", description = "", price = "S/ 15.99 / kg", rating = 4, imageRes = R.drawable.carnes_pollo, category = "Carnes", discount = "10%", cantidad = 120)
    )

    NegolatinaTheme {
        InventoryProductListScreen(
            navController = rememberNavController(),
            categoryName = "Carnes",
            products = fakeProducts,
            onDeleteProduct = {}
        )
    }
}
