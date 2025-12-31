package com.negolatina.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImprovedShoppingCartScreen(navController: NavController) {
    val cartItems = CartManager.items
    val totalPrice = CartManager.total

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carrito de Compras", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = Color.White)
                } },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFF0000))
            )
        },
        bottomBar = {
            if (cartItems.isNotEmpty()) {
                Surface(
                    color = Color.White,
                    shadowElevation = 16.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Total:", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            Text("S/ ${"%.2f".format(totalPrice)}", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFFFF0000))
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { navController.navigate("checkout") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF0000)),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Proceder al Pago", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        if (cartItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.carro_vacio),
                        contentDescription = "Carrito vacío",
                        modifier = Modifier.size(500.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Tu carrito está vacío", fontSize = 18.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { navController.navigate("home") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF0000))
                    ) {
                        Text("Ir a comprar")
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFF5F5F5)),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(cartItems) { item ->
                    CartItemRow(
                        item = item,
                        onIncrement = { CartManager.incrementQuantity(item.product.id) },
                        onDecrement = { CartManager.decrementQuantity(item.product.id) },
                        onRemove = { CartManager.removeProduct(item.product.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun CartItemRow(
    item: CartItem,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val imageToShow = if (item.product.imageRes != 0) item.product.imageRes else R.drawable.logo_pollito
            
            if (item.product.imageUri != null) {
                AsyncImage(
                    model = item.product.imageUri,
                    contentDescription = item.product.title,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(id = imageToShow),
                    contentDescription = item.product.title,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.product.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1
                )
                Text(
                    text = item.product.price,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                
                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onDecrement,
                        modifier = Modifier
                            .size(30.dp)
                            .background(Color(0xFFEEEEEE), RoundedCornerShape(4.dp))
                    ) {
                        Icon(Icons.Default.Remove, contentDescription = "Menos", modifier = Modifier.size(16.dp))
                    }
                    
                    Text(
                        text = item.quantity.toString(),
                        modifier = Modifier.padding(horizontal = 12.dp),
                        fontWeight = FontWeight.Bold
                    )
                    
                    IconButton(
                        onClick = onIncrement,
                        modifier = Modifier
                            .size(30.dp)
                            .background(Color(0xFFEEEEEE), RoundedCornerShape(4.dp))
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Más", modifier = Modifier.size(16.dp))
                    }
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                IconButton(onClick = onRemove) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
                }
                Text(
                    text = "S/ ${"%.2f".format(item.subtotal)}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFFFF0000)
                )
            }
        }
    }
}
