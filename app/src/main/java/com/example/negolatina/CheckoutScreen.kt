package com.example.negolatina

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(navController: NavController) {
    var address by remember { mutableStateOf("") }
    var paymentMethod by remember { mutableStateOf("Tarjeta") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Finalizar compra", color = Color.White) },
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
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Dirección
            Text("Dirección de entrega", fontWeight = FontWeight.Bold)
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                placeholder = { Text("Ej: Av. Siempre Viva 123") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(20.dp))

            // Método de pago
            Text("Método de pago", fontWeight = FontWeight.Bold)
            PaymentSelector(paymentMethod) { paymentMethod = it }

            Spacer(Modifier.height(20.dp))

            // Resumen de compra
            PurchaseSummary()

            Spacer(Modifier.height(30.dp))

            // Botón final
            Button(
                onClick = {
                    CartManager.clear()
                    navController.navigate("purchase_success") {
                        popUpTo("checkout") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE31E24)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Finalizar compra", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun PaymentSelector(selected: String, onSelected: (String) -> Unit) {
    val methods = listOf("Tarjeta", "Yape", "Plin", "Contra entrega")

    Column {
        methods.forEach { method ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selected == method,
                    onClick = { onSelected(method) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFFE31E24)
                    )
                )
                Text(method)
            }
        }
    }
}

@Composable
fun PurchaseSummary() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("Resumen del pedido", fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Subtotal")
                Text("S/. ${"%.2f".format(CartManager.total)}")
            }

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Envío")
                Text("S/. 5.00")
            }

            Divider(Modifier.padding(vertical = 8.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total a pagar", fontWeight = FontWeight.Bold)
                Text(
                    "S/. ${"%.2f".format(CartManager.total + 5)}",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
