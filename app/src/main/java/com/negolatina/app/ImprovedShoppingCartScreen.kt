package com.negolatina.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImprovedShoppingCartScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carrito de compras", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            "Atrás",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFE31E24)
                )
            )
        },
        bottomBar = {
            if (!CartManager.isEmpty()) {
                CartBottomBar(navController)
            }
        }
    ) { padding ->
        if (CartManager.isEmpty()) {
            EmptyCartView()
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                items(CartManager.items) { cartItem ->
                    CartItemRow(cartItem)
                }
            }
        }
    }
}

@Composable
fun EmptyCartView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Tu carrito está vacío",
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
    }
}

@Composable
fun CartBottomBar(navController: NavController) {
    Surface(
        shadowElevation = 10.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Total", fontWeight = FontWeight.Medium)
                Text(
                    "S/. ${"%.2f".format(CartManager.total)}",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                )
            }

            Button(
                onClick = { navController.navigate("checkout") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE31E24)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Continuar", modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
fun CartItemRow(cartItem: CartItem) {
    val product = cartItem.product

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF9F9F9))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = product.title,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(product.title, fontWeight = FontWeight.Bold)
            Text(product.price, color = Color.Gray)

            Spacer(Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { CartManager.decrementQuantity(product.id) }) {
                    Icon(Icons.Default.Remove, "Restar")
                }

                Text(
                    cartItem.quantity.toString(),
                    fontWeight = FontWeight.Bold
                )

                IconButton(onClick = { CartManager.incrementQuantity(product.id) }) {
                    Icon(Icons.Default.Add, "Sumar")
                }
            }
        }

        IconButton(onClick = { CartManager.removeProduct(product.id) }) {
            Icon(Icons.Default.Delete, "Eliminar", tint = Color.Red)
        }
    }
}
