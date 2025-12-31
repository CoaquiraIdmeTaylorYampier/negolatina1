package com.negolatina.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(
    navController: NavController, 
    profileViewModel: ProfileViewModel = viewModel(), 
    productViewModel: ProductViewModel = viewModel()
) {
    val negoLatinaRed = Color(0xFFFF0000)

    val adminName by profileViewModel.name
    val products by productViewModel.products.collectAsState()
    val adminProfileImage by profileViewModel.profileImageRes
    var selectedItem by remember { mutableIntStateOf(0) }
    
    var showLogoutDialog by remember { mutableStateOf(false) }

    if (showLogoutDialog) {
        LogoutConfirmationDialog(
            onConfirm = {
                showLogoutDialog = false
                navController.navigate("welcome") {
                    popUpTo("admin_dashboard") { inclusive = true }
                }
            },
            onDismiss = { showLogoutDialog = false }
        )
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(containerColor = Color.White) {
                val navItems = listOf("Inicio", "Inventario", "Pedidos")
                val navIcons = listOf(Icons.Default.Home, Icons.Default.Inventory, Icons.Default.ShoppingBag)

                navItems.forEachIndexed { index, title ->
                    val isSelected = selectedItem == index
                    IconButton(
                        onClick = {
                            selectedItem = index
                            when (title) {
                                "Inventario" -> navController.navigate("inventory_categories")
                                "Pedidos" -> navController.navigate("admin_orders")
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = navIcons[index],
                                contentDescription = title,
                                tint = if (isSelected) negoLatinaRed else Color.Gray
                            )
                            Text(
                                text = title,
                                fontSize = 10.sp,
                                color = if (isSelected) negoLatinaRed else Color.Gray
                            )
                        }
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .verticalScroll(rememberScrollState())
        ) {

            // HEADER
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(negoLatinaRed, shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                    .padding(20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = adminProfileImage),
                        contentDescription = "Foto de perfil del admin",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Hola, Admin", color = Color.White)
                        Text(adminName, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                    IconButton(onClick = { showLogoutDialog = true }) {
                        Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Cerrar Sesión", tint = Color.White)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Buscar...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(10.dp)),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("  Resumen del día", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(start = 10.dp))
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .padding(end = 5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Icon(Icons.Default.AttachMoney, contentDescription = null, tint = negoLatinaRed)
                        Text("S/ 1,250", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Text("Ventas hoy", fontSize = 12.sp, color = Color.Gray)
                    }
                }

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .padding(start = 5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Icon(Icons.Default.PendingActions, contentDescription = null, tint = Color(0xFFFF9800))
                        Text("12", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Text("Pendientes", fontSize = 12.sp, color = Color.Gray)
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .padding(end = 5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Icon(Icons.Default.Visibility, contentDescription = null, tint = negoLatinaRed)
                        Text("340", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Text("Visitas", fontSize = 12.sp, color = Color.Gray)
                    }
                }

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .padding(start = 5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Icon(Icons.Default.Inventory2, contentDescription = null, tint = negoLatinaRed)
                        Text(products.size.toString(), fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Text("Productos", fontSize = 12.sp, color = Color.Gray)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("  Acciones Rápidas", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(start = 10.dp))
            
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .horizontalScroll(rememberScrollState())
            ) {
                Card(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 10.dp)
                        .clickable { navController.navigate("add_product") },
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = negoLatinaRed)
                        Text("Nuevo", fontSize = 12.sp)
                    }
                }

                Card(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 10.dp)
                        .clickable { navController.navigate("discount_products") },
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(Icons.Default.LocalOffer, contentDescription = null, tint = negoLatinaRed)
                        Text("Oferta", fontSize = 12.sp)
                    }
                }

                /*
                // aun no por ahora (scaner)
                Card(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(Icons.Default.QrCodeScanner, contentDescription = null, tint = negoLatinaRed)
                        Text("Escanear", fontSize = 12.sp)
                    }
                }
                */
                
                Card(
                    modifier = Modifier.size(100.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(Icons.Default.PointOfSale, contentDescription = null, tint = negoLatinaRed)
                        Text("Cerrar Caja", fontSize = 12.sp)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAdminDashboardSimple() {
    AdminDashboardScreen(navController = rememberNavController())
}
