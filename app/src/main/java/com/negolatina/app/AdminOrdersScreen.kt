package com.negolatina.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminOrdersScreen(navController: NavController, orderViewModel: OrderViewModel = viewModel()) {
    val orders by orderViewModel.orders.collectAsState()
    val userNames by orderViewModel.userNames.collectAsState() // Obtenemos el mapa de nombres

    DisposableEffect(orderViewModel) {
        orderViewModel.listenToAllOrders()
        onDispose {
            orderViewModel.stopListening()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de Pedidos", color = Color.White) },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Atrás", tint = Color.White)
                } },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFE31E24))
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(orders) { order ->
                // Pasamos el mapa de nombres a la tarjeta
                OrderCard(order = order, orderViewModel = orderViewModel, userNames = userNames)
            }
        }
    }
}

@Composable
fun OrderCard(order: Order, orderViewModel: OrderViewModel, userNames: Map<String, String>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (order.status) {
                OrderStatus.ACCEPTED.name -> Color(0xFFE8F5E9)
                OrderStatus.REJECTED.name -> Color(0xFFFFEBEE)
                else -> Color.White
            }
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Pedido #${order.id.take(6).uppercase()}", fontWeight = FontWeight.Bold)
            
            // Mostramos el nombre del cliente
            val clientName = userNames[order.userId] ?: "Usuario Desconocido"
            Text("Cliente: $clientName")
            
            Spacer(modifier = Modifier.height(8.dp))
            Text("Estado: ${order.status}", fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(12.dp))
            Text("Productos:", fontWeight = FontWeight.Medium)
            order.items.forEach { item ->
                Row {
                    Text("${item.quantity} x ")
                    Text(item.title)
                }
            }
            
            if (order.status == OrderStatus.PENDING.name) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(), 
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { orderViewModel.rejectOrder(order.id) }) {
                        Text("Rechazar", color = Color.Red)
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                    Button(
                        onClick = { orderViewModel.acceptOrder(order) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                    ) {
                        Text("Aceptar")
                    }
                }
            }
        }
    }
}
