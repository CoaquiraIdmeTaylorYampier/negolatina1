package com.negolatina.app

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(navController: NavController) {
    var address by remember { mutableStateOf("") }
    var paymentMethod by remember { mutableStateOf("Tarjeta") }
    var addressError by remember { mutableStateOf(false) }

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
                .verticalScroll(rememberScrollState())
        ) {
            // Dirección de entrega
            Text(
                "Dirección de entrega",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = address,
                onValueChange = {
                    address = it
                    addressError = false
                },
                placeholder = { Text("Ej: Av. Siempre Viva 123, Juliaca") },
                modifier = Modifier.fillMaxWidth(),
                isError = addressError,
                supportingText = if (addressError) {
                    { Text("Por favor ingresa una dirección válida") }
                } else null,
                leadingIcon = {
                    Icon(Icons.Default.LocationOn, "Dirección")
                }
            )

            Spacer(Modifier.height(24.dp))

            // Método de pago
            Text(
                "Método de pago",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(Modifier.height(8.dp))

            PaymentSelector(paymentMethod) { paymentMethod = it }

            Spacer(Modifier.height(24.dp))

            // Resumen de compra
            PurchaseSummary()

            Spacer(Modifier.height(30.dp))

            // Botón continuar
            Button(
                onClick = {
                    if (address.trim().length < 10) {
                        addressError = true
                    } else {
                        // Navegar a la pantalla de método de pago correspondiente
                        when (paymentMethod) {
                            "Tarjeta" -> navController.navigate("payment_card/$address")
                            "Yape" -> navController.navigate("payment_yape/$address")
                            "Plin" -> navController.navigate("payment_plin/$address")
                            "Contra entrega" -> navController.navigate("payment_cash/$address")
                        }
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
                Text("Continuar al pago", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun PaymentSelector(selected: String, onSelected: (String) -> Unit) {
    val methods = listOf(
        "Tarjeta" to Icons.Default.CreditCard,
        "Yape" to Icons.Default.Smartphone,
        "Plin" to Icons.Default.PhoneAndroid,
        "Contra entrega" to Icons.Default.LocalShipping
    )

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        methods.forEach { (method, icon) ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelected(method) },
                colors = CardDefaults.cardColors(
                    containerColor = if (selected == method)
                        Color(0xFFFFEBEE)
                    else
                        Color.White
                ),
                border = if (selected == method)
                    androidx.compose.foundation.BorderStroke(2.dp, Color(0xFFE31E24))
                else
                    androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        icon,
                        contentDescription = method,
                        tint = if (selected == method) Color(0xFFE31E24) else Color.Gray
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        method,
                        fontWeight = if (selected == method) FontWeight.Bold else FontWeight.Normal,
                        modifier = Modifier.weight(1f)
                    )
                    RadioButton(
                        selected = selected == method,
                        onClick = { onSelected(method) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFFE31E24)
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun PurchaseSummary() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                "Resumen del pedido",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(Modifier.height(12.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Subtotal", color = Color.Gray)
                Text("S/. ${"%.2f".format(CartManager.total)}")
            }

            Spacer(Modifier.height(4.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Envío", color = Color.Gray)
                Text("S/. 5.00")
            }

            Divider(Modifier.padding(vertical = 12.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Total a pagar",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    "S/. ${"%.2f".format(CartManager.total + 5)}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFFE31E24)
                )
            }
        }
    }
}