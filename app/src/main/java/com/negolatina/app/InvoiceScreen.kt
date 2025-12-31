package com.negolatina.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoiceScreen(
    navController: NavController, 
    orderId: String,
    orderViewModel: OrderViewModel = viewModel(),
    authViewModel: AuthViewModel = viewModel()
) {

    val allOrders by orderViewModel.orders.collectAsState()
    val userOrders by orderViewModel.userOrders.collectAsState()
    val userNames by orderViewModel.userNames.collectAsState()
    val loginState by authViewModel.loginState.collectAsState()

    val isAdmin = (loginState as? LoginState.Success)?.user?.isAdmin ?: false

    DisposableEffect(orderViewModel, isAdmin) {
        if (isAdmin) {
            orderViewModel.listenToAllOrders()
        } else {
            orderViewModel.listenToUserOrders()
        }
        onDispose {
            orderViewModel.stopListening()
        }
    }

    val orders = if (isAdmin) allOrders else userOrders
    val order = orders.find { it.id == orderId }
    
    val clientName = order?.let { userNames[it.userId] } ?: ""

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Boleta de Venta Electrónica", color = Color.White, fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Atrás", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFE31E24))
            )
        },
        bottomBar = {
            if (order != null) {
                Button(
                    onClick = { 
                        if(isAdmin) navController.popBackStack() else navController.navigate("home") {
                            popUpTo("home") { inclusive = true }
                        }
                     },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE31E24))
                ) {
                    Text(if(isAdmin) "Volver a Pedidos" else "Volver al Inicio", fontWeight = FontWeight.Bold)
                }
            }
        }
    ) { padding ->
        if (order == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
                Text("Cargando boleta...", modifier = Modifier.padding(top = 70.dp))
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(Modifier.padding(20.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.logo_pollito),
                                contentDescription = "Logo",
                                modifier = Modifier.size(60.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text("NegoLatina Mini Market", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                                Text("RUC: 20123456789", fontSize = 12.sp, color = Color.Gray)
                            }
                        }

                        Divider(Modifier.padding(vertical = 16.dp))

                        Text("DATOS DEL CLIENTE", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Cliente: $clientName")
                        Text("Dirección: ${order.address}")

                        Divider(Modifier.padding(vertical = 16.dp))

                        Text("DETALLES DEL PEDIDO", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
                        Text("Nº de Boleta: ${order.id.take(8).uppercase()}")
                        Text("Fecha: ${dateFormat.format(Date(order.timestamp))}")
                        Text("Método de Pago: ${order.paymentMethod}")

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(Modifier.fillMaxWidth().background(Color(0xFFF0F0F0)).padding(8.dp)) {
                            Text("Producto", modifier = Modifier.weight(2f), fontWeight = FontWeight.Bold)
                            Text("Cant.", modifier = Modifier.weight(0.5f), fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                            Text("Total", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, textAlign = TextAlign.End)
                        }

                        var subtotal = 0.0
                        order.items.forEach { item ->
                            val itemTotal = item.quantity * item.price
                            subtotal += itemTotal
                            Row(Modifier.fillMaxWidth().padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text(item.title, modifier = Modifier.weight(2f), fontSize = 14.sp)
                                Text(item.quantity.toString(), modifier = Modifier.weight(0.5f), textAlign = TextAlign.Center)
                                Text("S/ ${"%.2f".format(itemTotal)}", modifier = Modifier.weight(1f), textAlign = TextAlign.End)
                            }
                        }

                        Divider(Modifier.padding(vertical = 16.dp))

                        val shippingCost = 5.00

                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            Text("Subtotal: ", color = Color.Gray)
                            Text("S/ ${"%.2f".format(subtotal)}")
                        }
                         Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            Text("Envío: ", color = Color.Gray)
                            Text("S/ ${"%.2f".format(shippingCost)}")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                         Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                            Text("TOTAL: ", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text("S/ ${"%.2f".format(order.total)}", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFFE31E24))
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            "Gracias por su compra en NegoLatina",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center, 
                            color = Color.Gray, 
                            fontSize = 12.sp
                        )

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InvoiceScreenPreview() {
    InvoiceScreen(navController = rememberNavController(), orderId = "dummy-order-id")
}
