package com.negolatina.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.negolatina.app.ui.theme.NegolatinaTheme

@Preview(showBackground = true, name = "Pantalla de Ayuda")
@Composable
fun HelpScreenPreview() {
    NegolatinaTheme {
        HelpScreen(navController = rememberNavController())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen(navController: NavController) {
    var expandedSection by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // TopAppBar consistente con el resto de la app
        TopAppBar(
            title = { Text("Centro de Ayuda", color = Color.White, fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, "Atrás", tint = Color.White)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFE31E24))
        )

        // Contenido scrolleable
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "Preguntas Frecuentes",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            )

            Spacer(Modifier.height(16.dp))

            // Secciones de ayuda
            HelpSection(
                title = "¿Cómo crear una cuenta?",
                expanded = expandedSection == "registro",
                onClick = { expandedSection = if (expandedSection == "registro") null else "registro" }
            ) {
                Text(
                    text = "1. Presiona 'Registrarse' en la pantalla de bienvenida\n" +
                            "2. Completa tus datos personales (nombre, correo, teléfono)\n" +
                            "3. Crea una contraseña segura\n" +
                            "4. Acepta los términos y condiciones\n" +
                            "5. Presiona 'Crear cuenta' para finalizar",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF666666),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }

            HelpSection(
                title = "¿Cómo realizar una compra?",
                expanded = expandedSection == "compra",
                onClick = { expandedSection = if (expandedSection == "compra") null else "compra" }
            ) {
                Text(
                    text = "1. Navega por las categorías o busca productos\n" +
                            "2. Selecciona el producto que deseas\n" +
                            "3. Ajusta la cantidad y presiona 'Agregar al carrito'\n" +
                            "4. Ve al carrito de compras usando el ícono en la parte superior\n" +
                            "5. Revisa tu pedido y presiona 'Proceder al pago'\n" +
                            "6. Completa los datos de envío y pago\n" +
                            "7. Confirma tu orden",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF666666),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }

            HelpSection(
                title = "¿Cómo vender productos?",
                expanded = expandedSection == "vender",
                onClick = { expandedSection = if (expandedSection == "vender") null else "vender" }
            ) {
                Text(
                    text = "1. Crea una cuenta de vendedor o administrador\n" +
                            "2. Ve a la sección 'Vender' desde el menú\n" +
                            "3. Completa la información del producto (nombre, descripción, precio)\n" +
                            "4. Sube fotos de calidad del producto\n" +
                            "5. Selecciona la categoría apropiada\n" +
                            "6. Publica tu producto\n" +
                            "7. Gestiona tus ventas desde 'Mi cuenta Admin'",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF666666),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }

            HelpSection(
                title = "Métodos de pago disponibles",
                expanded = expandedSection == "pago",
                onClick = { expandedSection = if (expandedSection == "pago") null else "pago" }
            ) {
                Text(
                    text = "Aceptamos los siguientes métodos de pago:\n\n" +
                            "• Tarjetas de crédito y débito (Visa, Mastercard)\n" +
                            "• Transferencia bancaria\n" +
                            "• Pago contra entrega\n" +
                            "• Billeteras digitales (Yape, Plin)\n\n" +
                            "Todos los pagos son procesados de forma segura y encriptada.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF666666),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }

            HelpSection(
                title = "Política de devoluciones",
                expanded = expandedSection == "devoluciones",
                onClick = { expandedSection = if (expandedSection == "devoluciones") null else "devoluciones" }
            ) {
                Text(
                    text = "Tienes 7 días para solicitar una devolución desde la fecha de entrega.\n\n" +
                            "Requisitos:\n" +
                            "• El producto debe estar en su empaque original\n" +
                            "• No debe estar usado o dañado\n" +
                            "• Conservar el comprobante de compra\n\n" +
                            "Para iniciar una devolución, contacta con nuestro servicio al cliente desde la sección 'Mis compras'.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF666666),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }

            HelpSection(
                title = "Tiempos de entrega",
                expanded = expandedSection == "entrega",
                onClick = { expandedSection = if (expandedSection == "entrega") null else "entrega" }
            ) {
                Text(
                    text = "Los tiempos de entrega varían según tu ubicación:\n\n" +
                            "• Zona urbana (Lima): 1-3 días hábiles\n" +
                            "• Zona suburbana: 3-5 días hábiles\n" +
                            "• Zona rural o provincias: 5-7 días hábiles\n\n" +
                            "Recibirás notificaciones sobre el estado de tu pedido en la sección 'Notificaciones'.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF666666),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }

            HelpSection(
                title = "Seguridad de la cuenta",
                expanded = expandedSection == "seguridad",
                onClick = { expandedSection = if (expandedSection == "seguridad") null else "seguridad" }
            ) {
                Text(
                    text = "Recomendaciones de seguridad:\n\n" +
                            "• Usa una contraseña única y segura\n" +
                            "• No compartas tus credenciales con nadie\n" +
                            "• Cierra sesión en dispositivos compartidos\n" +
                            "• Revisa regularmente tu historial de compras\n" +
                            "• Verifica que los correos sean oficiales de Negolatina\n\n" +
                            "Si sospechas actividad no autorizada, cambia tu contraseña inmediatamente y contacta con soporte.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF666666),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }

            HelpSection(
                title = "¿Cómo usar las notificaciones?",
                expanded = expandedSection == "notificaciones",
                onClick = { expandedSection = if (expandedSection == "notificaciones") null else "notificaciones" }
            ) {
                Text(
                    text = "Las notificaciones te mantienen informado sobre:\n\n" +
                            "• Estado de tus pedidos\n" +
                            "• Ofertas y descuentos especiales\n" +
                            "• Nuevos productos en tus categorías favoritas\n" +
                            "• Mensajes de vendedores\n\n" +
                            "Accede a tus notificaciones desde el menú principal o la campana en la parte superior.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF666666),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }

            HelpSection(
                title = "Modo Ecológico",
                expanded = expandedSection == "eco",
                onClick = { expandedSection = if (expandedSection == "eco") null else "eco" }
            ) {
                Text(
                    text = "El Modo Ecológico te ayuda a:\n\n" +
                            "• Cuidar el Medio Ambiente\n" +
                            "• Apoyar a vendedores con prácticas ecológicas\n" +
                            "• Reducir la huella de carbono en tus compras\n\n" +
                            "Activa el Modo Ecológico desde el menú principal para ver tu amigo sostenible.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF666666),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }

            Spacer(Modifier.height(24.dp))

            // Card de contacto con el estilo de la app
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3F3))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "¿Necesitas más ayuda?",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE31E24)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Nuestro equipo está listo para ayudarte",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF666666)
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "📧 soporte@negolatina.com\n" +
                                "📱 WhatsApp: +51 999 888 777\n" +
                                "⏰ Lun-Vie: 9:00 AM - 6:00 PM\n" +
                                "⏰ Sáb: 9:00 AM - 1:00 PM",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF333333),
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun HelpSection(
    title: String,
    expanded: Boolean,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFAFAFA)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF333333),
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painter = painterResource(
                        id = if (expanded) android.R.drawable.arrow_up_float
                        else android.R.drawable.arrow_down_float
                    ),
                    contentDescription = if (expanded) "Contraer" else "Expandir",
                    tint = Color(0xFFE31E24)
                )
            }

            if (expanded) {
                Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                content()
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}