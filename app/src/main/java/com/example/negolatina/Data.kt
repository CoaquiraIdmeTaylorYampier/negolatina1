package com.example.negolatina

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Product(
    val id: String,
    val title: String,
    val description: String,
    val price: String,
    val rating: Int,
    @DrawableRes val imageRes: Int,
    val category: String,
    val discount: String? = null
)

data class Category(
    val name: String,
    val route: String,
    @DrawableRes val imageRes: Int,
    val color: Color
)





val allCategories = listOf(
    Category("Bebidas", "drinks", R.drawable.power, Color(0xFF00D5E3)),
    Category("Frutas", "fruits_vegetables", R.drawable.uva, Color(0xFFF900FF)),
    Category("Carne", "meats_sausages", R.drawable.carnemolida, Color(0xFFFFC700)),
    Category("Lácteos", "dairy_eggs", R.drawable.leche, Color(0xFF8B4513)),
    Category("Snacks", "snacks", R.drawable.pringles, Color(0xFF4CAF50)),
    Category("Panadería", "bakery", R.drawable.panintegral, Color(0xFFFF9800)),
    Category("Limpieza", "cleaning_home", R.drawable.guantes, Color(0xFF2196F3)),
    Category("Abarrotes", "grains_groceries", R.drawable.arroz, Color(0xFF9C27B0))
)

val allProducts = listOf(
    // Carnes
    Product("c1", "Chuletas de cerdo", "Cortes frescos de carne de cerdo, ideales para parrillas, guisos y recetas caseras.", "S/.16.90 x Kg", 3, R.drawable.carne_res, "Carnes", "5%"),
    Product("c2", "Pollo Entero", "Pollo fresco y de granja, listo para hornear o trozar para tus guisos.", "S/.12.50 x Kg", 4, R.drawable.pollo, "Carnes"),
    Product("c3", "Bisteck de Res", "Finos cortes de res para freír, apanar o para un delicioso lomo saltado.", "S/.22.00 x Kg", 5, R.drawable.bisteck, "Carnes"),

    // Bebidas
    Product("b1", "SMIRNOFF", "Vodka triplemente destilado y filtrado diez veces, conocido por su suavidad y versatilidad.", "S/.25.40", 5, R.drawable.smirnoff, "Bebidas"),
    Product("b2", "Coca Cola 2.5L", "Bebida gaseosa refrescante para compartir en familia.", "S/.7.50", 5, R.drawable.cola, "Bebidas"),
    Product("b3", "Jack Daniels", "Whiskey de Tennessee con un sabor suave y característico a roble y caramelo.", "S/.85.00", 5, R.drawable.jack, "Bebidas"),

    // Frutas y Verduras
    Product("fv1", "Manzana Roja", "Manzana roja de importación, dulce y crujiente, ideal para comer sola o en postres.", "S/.5.40 x Kg", 4, R.drawable.manzana, "Frutas y Verduras"),
    Product("fv2", "Plátanos de Seda", "Presentacion 0.50 kg aprox. Ricos en potasio y energía.", "S/.2.50", 3, R.drawable.platano, "Frutas y Verduras", "15%"),
    Product("fv3", "Tomate Italiano", "Tomate fresco y jugoso, perfecto para ensaladas, salsas y guisos.", "S/.3.00 x Kg", 4, R.drawable.tomate, "Frutas y Verduras"),

    // Abarrotes
    Product("a1", "Avena tres ositos", "Hojuelas de avena pre-cocida de grano entero, fuente de fibra y proteína para un desayuno saludable.", "S/.4.50", 4, R.drawable.harina, "Abarrotes", "25%"),
    Product("a2", "Arroz Costeño", "Arroz extra, ideal para acompañar todos tus platos. Bolsa de 5kg.", "S/.18.00", 5, R.drawable.arroz, "Abarrotes"),
    
    // Lácteos
    Product("l1", "Leche Gloria", "Leche evaporada entera UHT, enriquecida con vitaminas A y D. Indispensable en tu cocina.", "S/.3.90", 5, R.drawable.leche, "Lácteos"),
    Product("l2", "Yogurt Gloria Fresa", "Yogurt bebible con auténticos trozos de fruta, ideal para el desayuno o como un snack refrescante.", "S/.6.50", 4, R.drawable.yogurt, "Lácteos")
)
