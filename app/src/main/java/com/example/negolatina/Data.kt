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

//  items del menu
data class DrawerMenuItem(
    val route: String,
    val title: String,
    val icon: ImageVector
)

// modelo pa' usuarios
data class User(
    val id: String,
    val name: String,
    val email: String,
    val isAdmin: Boolean = false
)


// Base d usuarios
val allUsers = listOf(
    User(id = "1", name = "Vidal", email = "vidal@cliente.com", isAdmin = false),
    User(id = "2", name = "admin", email = "admin@negolatina.com", isAdmin = true)
)

val allCategories = listOf(
    Category("Bebidas", "drinks", R.drawable.bebida_general, Color(0xFF00D5E3)),
    Category("Frutas y Verduras", "fruits_vegetables",R.drawable.frutas_y_verduras_general, Color(0xFFF900FF)),
    Category("Carnes", "meats_sausages", R.drawable.carnes_general, Color(0xFFFFC700)),
    Category("Lácteos", "dairy_eggs", R.drawable.lacteosgeneral, Color(0xFF8B4513)),
    Category("Snacks", "snacks", R.drawable.snacksgeneral, Color(0xFF4CAF50)),
    Category("Panadería", "bakery", R.drawable.panaderiageneral, Color(0xFFFF9800)),
    Category("Limpieza", "cleaning_home", R.drawable.limpiezageneral, Color(0xFF2196F3)),
    Category("Abarrotes", "grains_groceries", R.drawable.abarrotesgeneral, Color(0xFF9C27B0)),
    Category("Emprendedores Locales", "Local_Emprend", R.drawable.emprendedores_locales,Color(0xFAD12387)) //imagen referencial, cambiar luego

)

val allProducts = listOf(
    // Carnes
    Product("c1", "Chuletas de cerdo", "Cortes frescos de carne de cerdo, ideales para parrillas, guisos y recetas caseras.", "S/.16.90 x Kg", 3, R.drawable.chuletacerdo, "Carnes", "5%"),
    Product("c2", "Pollo Entero", "Pollo fresco y de granja, listo para hornear o trozar para tus guisos.", "S/.12.50 x Kg", 4, R.drawable.pollo, "Carnes"),
    Product("c3", "Bisteck de Res", "Finos cortes de res para freír, apanar o para un delicioso lomo saltado.", "S/.22.00 x Kg", 5, R.drawable.bisteck, "Carnes"),
    Product("c4", "Salchichas San Bernardo 10u", "Pack de salchichas de la marca San Bernardo, ideal para preparando hot dogs, sánguches o acompañar comidas rápidas.", "S/.8.00", 3, R.drawable.hotdog, "Carnes", "5%"),
    Product("c5", "Carne Molida", "Carne molida fresca, versátil para preparar hamburguesas, guisos, albóndigas o rellenos caseros.", "S/. 14.50 x Kg", 5, R.drawable.carnemolida, "Carnes"),
    Product("c6", "Jamón", "Jamón tierno y listo para consumir, perfecto para sándwiches, desayunos, ensaladas o como complemento de tablas de fiambres.", "S/.20.00 x Kg", 5, R.drawable.jamon, "Carnes"),
    // Bebidas
    Product("b1", "SMIRNOFF", "Vodka triplemente destilado y filtrado diez veces, conocido por su suavidad y versatilidad.", "S/.25.40", 5, R.drawable.smirnoff, "Bebidas"),
    Product("b2", "Coca Cola 2.5L", "Bebida gaseosa refrescante para compartir en familia.", "S/.7.50", 5, R.drawable.cola, "Bebidas"),
    Product("b3", "Jack Daniels", "Whiskey de Tennessee con un sabor suave y característico a roble y caramelo.", "S/.85.00", 5, R.drawable.jack, "Bebidas"),
    Product("b4", "Black Label", "Whisky escocés de 12 años con sabor equilibrado, notas de frutas maduras, caramelo, vainilla y un toque ahumado", "S/.120.00", 5, R.drawable.black, "Bebidas"),
    Product("b5", "Bacardi 750 ml", "Ron clásico, ligero y versátil, perfecto para cócteles, mezclas o momentos sociales.","S/.25.00" ,4, R.drawable.bacardi, "Bebidas"),
    Product("b6", "Powerade", "Bebida rehidratante ideal para recuperar energía y electrolitos después del ejercicio o en días calurosos.", "S/.15.00", 3, R.drawable.power, "Bebidas"),
    // Frutas y Verduras
    Product("fv1", "Manzana Roja", "Manzana roja de importación, dulce y crujiente, ideal para comer sola o en postres.", "S/.5.40 x Kg", 4, R.drawable.manzana, "Frutas y Verduras"),
    Product("fv2", "Plátanos de Seda", "Presentacion 0.50 kg aprox. Ricos en potasio y energía.", "S/.2.50", 3, R.drawable.platano, "Frutas y Verduras", "15%"),
    Product("fv3", "Tomate Italiano", "Tomate fresco y jugoso, perfecto para ensaladas, salsas y guisos.", "S/.3.00 x Kg", 4, R.drawable.tomate, "Frutas y Verduras"),
    Product("fv4", "Uvas Blancas", "Uvas blancas jugosas y dulces, perfectas para snack saludables, postres o acompañar comidas.", "S/. 6.50 x Kg", 5, R.drawable.uva, "Frutas y Verduras"),
    Product("fv5", "Cebolla Morada", "Cebolla morada fresca, ideal para ensaladas, guisos y salsas, aporta sabor intenso y textura crujiente.", "S/. 3.20 x Kg", 3, R.drawable.cebolla, "Frutas y Verduras", "15%"),
    Product("fv6", "Papaya", "Papaya madura, rica en vitaminas y fibra, ideal para desayunos, jugos o postres naturales.", "S/.4.80 x Kg", 5, R.drawable.papaya, "Frutas y Verduras"),
    // Abarrotes
    Product("a1", "Avena tres ositos", "Hojuelas de avena pre-cocida de grano entero, fuente de fibra y proteína para un desayuno saludable.", "S/.4.50", 4, R.drawable.avena, "Abarrotes", "25%"),
    Product("a2", "Arroz  M&K 750g", "Arroz extra, ideal para acompañar todos tus platos. Bolsa de 750g.", "S/.4.50", 5, R.drawable.arroz, "Abarrotes"),
    Product("a3", "Aceite Primor 1L", "Aceite vegetal de la marca Primor, ideal para freír, cocinar o preparar ensaladas y aderezos.", "S/.8.00", 3, R.drawable.aceite, "Abarrotes", "25%"),
    Product("a4", "Atún Marinero 170g", "Atún en lata “Marinero”, práctico para ensaladas, sánguches, pastas o comidas rápidas.", "S/.4.50", 5, R.drawable.atun, "Abarrotes"),
    Product("a5", "Azúcar Metro (1 kg)", "Azúcar común en presentación de 1 kg, indispensable para endulzar bebidas, postres y preparaciones de cocina.", "S/.3.20", 4, R.drawable.azucar, "Abarrotes", "25%"),
    Product("a6", "Aconcagua de durazno ", "Duraznos en conserva de la marca Aconcagua, listos para postres, ensaladas de fruta o recetas dulces.", "S/.9.50", 5, R.drawable.aconcagua, "Abarrotes"),
    Product("a7", "Tallarín Don Vittorio 450 g", "Tallarines de la marca Don Vittorio, pasta seca ideal para preparar pastas con salsas, sopas o guisos rápidos.", "S/.3.60", 4, R.drawable.tallarin, "Abarrotes"),
    // Lácteos
    Product("l1", "Leche Gloria", "Leche evaporada entera UHT, enriquecida con vitaminas A y D. Indispensable en tu cocina.", "S/.3.90", 5, R.drawable.leche, "Lácteos"),
    Product("l2", "Yogurt Gloria Fresa", "Yogurt bebible con auténticos trozos de fruta, ideal para el desayuno o como un snack refrescante.", "S/.6.50", 4, R.drawable.yogurt, "Lácteos"),
    Product("l3", "Carton de 30 huevos", "Cartón con 30 huevos frescos, fuente de proteína ideal para tortillas, desayunos, repostería o comidas familiares.", "S/.25.00", 3, R.drawable.huevos, "Lácteos"),
    Product("l4", "Queso fresco", "Queso fresco blanco, ideal para acompañar panes, preparar salsas, ensaladas o platos típicos peruanos.", "S/.18.00 x Kg", 5, R.drawable.queso, "Lácteos"),
    Product("l5", "Leche condensada Nestlé 393 g", "Leche condensada espesa y dulce, perfecta para postres, flanes, tortas, café o bebidas tradicionales.", "S/.7.50", 5, R.drawable.condensada, "Lácteos"),
    Product("l6", "Mantequilla barra 200g", "Mantequilla cremosa ideal para cocinar, hornear panes/pasteles o para untar en desayunos y meriendas.", "S/.4.80", 5, R.drawable.mantequilla, "Lácteos"),
    // Snacks
    Product("s1", "Cheetos Flaming Hot 120 g", "Cheetos sabor picante, crujientes y con un toque intenso de especias, ideales como snack para acompañar películas o reuniones.", "S/.5.50", 5, R.drawable.cheetos, "Snacks", ),
    Product("s2", "Papas Lays 36 g", "Bolsa pequeña de papas fritas Lays, perfectas para picar entre comidas o acompañar salsas y bebidas.", "S/.2.00", 4, R.drawable.papas, "Snacks"),
    Product("s3", "Piqueos 200g", "Mezcla de snacks tipo “piqueos”: pueden incluir mani, choclo tostado, palitos salados, etc. ideal como botana para compartir.", "S/. 6.00", 5, R.drawable.piqueo, "Snacks"),
    Product("s4", "Ole Ole", "Snack dulce/esponjoso de la marca Ole Ole — buen acompañamiento para fiestas, reuniones o antojos rápidos.", "S/.4.80", 4, R.drawable.oleole, "Snacks", ),
    Product("s5", "Pringles 104g", "Lata de papas Pringles, perfectas para picar en reuniones, viajes o como snack durante el día.", "S/. 10.00", 5, R.drawable.pringles, "Snacks"),
    Product("s6", "Batimix", "Snack mixto “Batimix”, mezcla de granos de chocolate y yogurt de compañia con un sabor dulce.", "S/.3.50 x Kg", 5, R.drawable.batimix, "Snacks"),
    // Limpieza
    Product("li1", "Clorox 1 galón", "Lavandina líquida de alta concentración, en envase de 1 galón, formulada para limpieza profunda y desinfección.", "S/.43.90 x Kg", 4, R.drawable.clorox, "Limpieza", ),
    Product("li2", "Advance White Pasta dental", "Crema dental con agentes blanqueadores, textura suave y presentación en tubo estándar.", "S/.15.00 x Kg", 4, R.drawable.pastadental, "Limpieza"),
    Product("li3", "Guantes de látex", "Guantes elásticos y resistentes hechos de látex, diseñados para protección en tareas de limpieza y uso doméstico.", "S/.5.00 x Kg", 5, R.drawable.guantes, "Limpieza"),
    Product("li4", "Kit Trapeador", "Trapeador con mecanismo giratorio de escurrido integrado y cubeta con sistema de pedal o presión para retirar el exceso de agua.", "S/.45.00", 4, R.drawable.trapeador, "Limpieza", ),
    Product("li5", "Limpia vidrios Sapolio", "Solución líquida transparente formulada para remover manchas de vidrio, con acabado sin residuos.", "S/. 10.00", 5, R.drawable.limpiavidrios, "Limpieza"),
    Product("li6", "Shampoo H&S 375 ml", "Shampoo anticaspa en presentación de 375 ml, con textura cremosa y fragancia fresca.", "S/.17.50 x Kg", 5, R.drawable.shampoo, "Limpieza"),
    // Panaderia
    Product("p1", "Pan de pulque", "Pan artesanal tradicional elaborado con masa fermentada con pulque, con textura blanda y corteza suave.", "S/.0.90 x Ud", 3, R.drawable.panpulque, "Panadería", ),
    Product("p2", "Pan de molde Bimbo", "Pan pre-envasado, rebanado y empacado, con miga uniforme.", "S/. 9.50", 5, R.drawable.panmolde, "Panadería"),
    Product("p3", "Pan cuernitos", "Pan pequeño de forma curvada (“cuernito”), masa suave y corteza ligera — típico de panaderías.", "S/. 0.40 x Ud", 5, R.drawable.pancuernitos, "Panadería"),
    Product("p4", "Harina blanca Blanca Flor 1kg", " Harina de trigo refinada, en polvo, para preparación de panes, repostería y masas.", "S/.6.00 x Kg", 4, R.drawable.harina, "Panadería", ),
    Product("p5", "Pan integral", "Pan hecho con harina integral, miga más densa y corteza más rústica.", "S/. 5.50 x Kg", 5, R.drawable.panintegral, "Panadería"),
    Product("p6", "Levadura Royal", "Polvo leudante (gasificantes) para masas, mezcla en seco con harina — viene en sobres o envase compacto.", "S/. 3.00", 3, R.drawable.levadura, "Panadería"),
    //Emprendedores Locales
    Product("EmpLoc1","Cafe Cola", "Bebida local con un sabor agradable y refrescante","S/. 2.50",4,R.drawable.cafe_cola,"Emprendedores Locales")

    )
