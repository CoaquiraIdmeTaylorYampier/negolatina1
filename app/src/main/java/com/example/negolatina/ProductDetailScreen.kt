package com.example.negolatina

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.negolatina.ui.theme.NegolatinaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(navController: NavController, productId: String) {

    val product = allProducts.find { it.id == productId } ?: allProducts.first()
    var quantity by remember { mutableStateOf(1) }
    var userRating by remember { mutableStateOf(product.rating) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del producto") },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                } },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFE31E24), titleContentColor = Color.White, navigationIconContentColor = Color.White)
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFF0F0F0),
                modifier = Modifier.height(70.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Cantidad", fontWeight = FontWeight.Medium)
                        Spacer(modifier = Modifier.width(16.dp))
                        IconButton(onClick = { if (quantity > 1) quantity-- }) {
                           Icon(Icons.Default.Remove, contentDescription = "Restar")
                        }
                        Text(quantity.toString(), fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        IconButton(onClick = { quantity++ }) {
                            Icon(Icons.Default.Add, contentDescription = "Añadir")
                        }
                    }
                    Button(
                        onClick = { /*  Lógica para añadir al carrito */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Añadir al carrito", modifier = Modifier.padding(vertical = 4.dp))
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .background(Color(0xFFF2DCD8))
            )

            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = product.imageRes),
                        contentDescription = product.title,
                        modifier = Modifier.size(300.dp),
                        contentScale = ContentScale.Fit
                    )
                    Surface(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp),
                        shape = RoundedCornerShape(8.dp),
                        color = Color.White,
                        shadowElevation = 4.dp
                    ) {
                        Text(
                            text = product.price,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(y = (-32).dp),
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(product.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(product.description, fontSize = 16.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.height(24.dp))
                        Text("Valoración", fontWeight = FontWeight.Medium)
                        Spacer(modifier = Modifier.height(8.dp))
                        RatingBar(
                            rating = userRating,
                            onRatingChanged = { newRating -> userRating = newRating }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RatingBar(rating: Int, onRatingChanged: (Int) -> Unit, maxRating: Int = 5) {
    Row {
        for (i in 1..maxRating) {
            IconButton(onClick = { onRatingChanged(i) }) {
                Icon(
                    imageVector = if (i <= rating) Icons.Filled.Star else Icons.Filled.StarBorder,
                    contentDescription = "Rate $i",
                    tint = if (i <= rating) Color(0xFFFFC700) else Color.Gray,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProductDetailScreenPreview() {
    NegolatinaTheme {
        ProductDetailScreen(navController = rememberNavController(), productId = "c1")
    }
}

@Preview(showBackground = true)
@Composable
fun InteractiveRatingBarPreview() {
    var rating by remember { mutableStateOf(3) }
    RatingBar(rating = rating, onRatingChanged = { rating = it })
}
