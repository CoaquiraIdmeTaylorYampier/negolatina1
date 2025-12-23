package com.negolatina.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
@Composable
fun ProductCard(product: Product, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("product/${product.id}") },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            // Imagen del producto
            if (product.imageUri != null) {
                AsyncImage(
                    model = product.imageUri,
                    contentDescription = product.title,
                    modifier = Modifier
                        .height(120.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.title,
                    modifier = Modifier
                        .height(120.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Título del producto
            Text(
                text = product.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Precio del producto
            Text(
                text = product.price,
                color = Color.Red,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Start)
            )

            // Badge de descuento (si existe)
            product.discount?.let { descuento ->
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "-$descuento",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clip(RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Tarjeta de Producto")
@Composable
fun ProductCardPreview() {
    val productoEjemplo = Product(
        id = "demo1",
        title = "Manzana Roja",
        description = "Manzanas frescas y deliciosas",
        price = "S/. 5.40 x Kg",
        rating = 4,
        imageRes = R.drawable.frutverd_manzana,
        category = "Frutas y Verduras",
        discount = "15%"
    )

    ProductCard(
        product = productoEjemplo,
        navController = rememberNavController()
    )
}
