package com.negolatina.app

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyOrdersScreen(navController: NavController, orderViewModel: OrderViewModel = viewModel()) {
    val userOrders by orderViewModel.userOrders.collectAsState()

    DisposableEffect(orderViewModel) {
        orderViewModel.listenToUserOrders()
        onDispose {
            orderViewModel.stopListening()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Pedidos", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Atrás", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFE31E24))
            )
        }
    ) { paddingValues ->
        if (userOrders.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Aún no has realizado ningún pedido.", color = Color.Gray)
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
                items(userOrders) { order ->
                    MyOrderCard(order = order, navController = navController)
                }
            }
        }
    }
}

@Composable
fun MyOrderCard(order: Order, navController: NavController) {
    val statusColor = when (order.status) {
        OrderStatus.ACCEPTED.name -> Color(0xFF4CAF50)
        OrderStatus.REJECTED.name -> Color.Red
        OrderStatus.COMPLETED.name -> Color.Gray
        else -> Color(0xFFFF9800) // PENDING
    }
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val dateString = dateFormat.format(Date(order.timestamp))

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Pedido #${order.id.take(6).uppercase()}", fontWeight = FontWeight.Bold)
                Text(dateString, fontSize = 12.sp, color = Color.Gray)
            }
            
            Spacer(modifier = Modifier.height(8.dp))

            Text("Estado: ${order.status}", color = statusColor, fontWeight = FontWeight.SemiBold)
            Text("Total: S/ ${"%.2f".format(order.total)}", fontWeight = FontWeight.Medium)

            Spacer(modifier = Modifier.height(12.dp))

            Text("Productos:", fontWeight = FontWeight.Medium)
            Column(modifier = Modifier.padding(start = 8.dp)) {
                order.items.forEach { item ->
                    Text("• ${item.quantity} x ${item.title}")
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedButton(
                onClick = { navController.navigate("invoice/${order.id}") },
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(1.dp, Color(0xFFE31E24))
            ) {
                Text("Ver Boleta", color = Color(0xFFE31E24))
            }
        }
    }
}
