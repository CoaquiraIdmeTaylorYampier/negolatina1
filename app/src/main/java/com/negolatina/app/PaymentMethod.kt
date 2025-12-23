package com.negolatina.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// ============= PANTALLA PAGO CON TARJETA =============
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardPaymentScreen(navController: NavController, address: String) {
    var cardNumber by remember { mutableStateOf("") }
    var cardName by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pago con Tarjeta", color = Color.White) },
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
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen de la tarjeta
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tarjeta),
                    contentDescription = "Tarjeta",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(Modifier.height(24.dp))

            // Total a pagar
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF5F5F5)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Total a pagar", color = Color.Gray)
                    Text(
                        "S/. ${"%.2f".format(CartManager.total + 5)}",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE31E24)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // Formulario
            OutlinedTextField(
                value = cardNumber,
                onValueChange = {
                    if (it.length <= 16 && it.all { char -> char.isDigit() }) {
                        cardNumber = it
                    }
                },
                label = { Text("Número de tarjeta") },
                placeholder = { Text("1234 5678 9012 3456") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(Icons.Default.CreditCard, "Tarjeta")
                },
                singleLine = true
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = cardName,
                onValueChange = { cardName = it.uppercase() },
                label = { Text("Nombre del titular") },
                placeholder = { Text("JUAN PEREZ LOPEZ") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(Icons.Default.Person, "Titular")
                },
                singleLine = true
            )

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = expiryDate,
                    onValueChange = {
                        if (it.length <= 5) {
                            expiryDate = if (it.length == 2 && !it.contains("/")) {
                                "$it/"
                            } else {
                                it
                            }
                        }
                    },
                    label = { Text("Vencimiento") },
                    placeholder = { Text("MM/AA") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    leadingIcon = {
                        Icon(Icons.Default.CalendarToday, "Fecha")
                    },
                    singleLine = true
                )

                OutlinedTextField(
                    value = cvv,
                    onValueChange = {
                        if (it.length <= 3 && it.all { char -> char.isDigit() }) {
                            cvv = it
                        }
                    },
                    label = { Text("CVV") },
                    placeholder = { Text("123") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.weight(1f),
                    leadingIcon = {
                        Icon(Icons.Default.Lock, "CVV")
                    },
                    singleLine = true
                )
            }

            Spacer(Modifier.height(32.dp))

            // Botón pagar
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
                enabled = cardNumber.length == 16 &&
                        cardName.isNotBlank() &&
                        expiryDate.length == 5 &&
                        cvv.length == 3,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE31E24)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Procesar pago", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

// ============= PANTALLA PAGO CON YAPE =============
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YapePaymentScreen(navController: NavController, address: String) {
    var phoneNumber by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pago con Yape", color = Color.White) },
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
                    containerColor = Color(0xFF722C8E)
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Total a pagar
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF5F0F8)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Total a pagar", color = Color.Gray)
                    Text(
                        "S/. ${"%.2f".format(CartManager.total + 5)}",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF722C8E)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // Instrucciones
            Text(
                "Escanea el código QR con tu app Yape",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))

            // QR Code
            Card(
                modifier = Modifier
                    .size(280.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.qryape),
                        contentDescription = "QR Yape",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // Info adicional
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF5F0F8)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "O yapea al número:",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "918 797 976",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF722C8E)
                    )
                    Text(
                        "NegoLatina Mini Market",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // Campo de teléfono
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = {
                    if (it.length <= 9 && it.all { char -> char.isDigit() }) {
                        phoneNumber = it
                    }
                },
                label = { Text("Tu número de celular") },
                placeholder = { Text("987654321") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
                prefix = { Text("+51 ") },
                leadingIcon = {
                    Icon(Icons.Default.Phone, "Teléfono")
                }
            )

            Spacer(Modifier.height(32.dp))

            // Botón confirmar
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
                enabled = phoneNumber.length == 9,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF722C8E)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Confirmar pago", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

// ============= PANTALLA PAGO CON PLIN =============
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlinPaymentScreen(navController: NavController, address: String) {
    var phoneNumber by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pago con Plin", color = Color.White) },
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
                    containerColor = Color(0xFF00BCD4)
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Total a pagar
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE0F7FA)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Total a pagar", color = Color.Gray)
                    Text(
                        "S/. ${"%.2f".format(CartManager.total + 5)}",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00BCD4)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // Instrucciones
            Text(
                "Escanea el código QR con tu app Plin",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))

            // QR Code (mismo que Yape)
            Card(
                modifier = Modifier
                    .size(280.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.qrplin),
                        contentDescription = "QR Plin",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // Info adicional
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE0F7FA)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "O realiza tu Plin al número:",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "918 797 976",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00BCD4)
                    )
                    Text(
                        "NegoLatina Mini Market",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // Campo de teléfono
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = {
                    if (it.length <= 9 && it.all { char -> char.isDigit() }) {
                        phoneNumber = it
                    }
                },
                label = { Text("Tu número de celular") },
                placeholder = { Text("987654321") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
                prefix = { Text("+51 ") },
                leadingIcon = {
                    Icon(Icons.Default.Phone, "Teléfono")
                }
            )

            Spacer(Modifier.height(32.dp))

            // Botón confirmar
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
                enabled = phoneNumber.length == 9,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00BCD4)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Confirmar pago", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

// ============= PANTALLA CONTRA ENTREGA =============
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CashOnDeliveryScreen(navController: NavController, address: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pago contra entrega", color = Color.White) },
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
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(20.dp))

            // Icono grande
            Icon(
                Icons.Default.LocalShipping,
                contentDescription = "Entrega",
                modifier = Modifier.size(120.dp),
                tint = Color(0xFFE31E24)
            )

            Spacer(Modifier.height(24.dp))

            Text(
                "Confirma tu dirección de entrega",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(24.dp))

            // Dirección
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF5F5F5)
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        "Dirección",
                        tint = Color(0xFFE31E24)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text("Dirección de entrega", fontSize = 12.sp, color = Color.Gray)
                        Text(
                            address,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // Total
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF5F5F5)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Total a pagar al recibir", color = Color.Gray)
                    Text(
                        "S/. ${"%.2f".format(CartManager.total + 5)}",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE31E24)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // Información importante
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFF3E0)
                )
            ) {
                Column(Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Info,
                            "Info",
                            tint = Color(0xFFFF9800)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "Información importante",
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(Modifier.height(12.dp))

                    Text("• Pagarás cuando recibas tu pedido", fontSize = 14.sp)
                    Spacer(Modifier.height(4.dp))
                    Text("• Ten el monto exacto listo", fontSize = 14.sp)
                    Spacer(Modifier.height(4.dp))
                    Text("• Tiempo de entrega: 30-45 minutos", fontSize = 14.sp)
                    Spacer(Modifier.height(4.dp))
                    Text("• Verifica tu pedido antes de pagar", fontSize = 14.sp)
                }
            }

            Spacer(Modifier.height(32.dp))

            // Botón confirmar
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
                Text("Confirmar pedido", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(Modifier.height(12.dp))

            TextButton(
                onClick = { navController.popBackStack() }
            ) {
                Text("Cambiar dirección")
            }
        }
    }
}