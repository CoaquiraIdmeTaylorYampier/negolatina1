package com.negolatina.app

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
    val discount: String? = null,
    val imageUri: String? = null
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
    User(id = "1", name = "Vidal", email = "vidal@cliente.com", isAdmin = true),
    User(id = "2", name = "lentes migajerazo", email = "lentes@gmail.com", isAdmin = false),
    User(id = "3", name = "Taylor", email = "coaquiraidmetaylor@gmail.com", isAdmin = true)
)

val allCategories = listOf(
    Category("Bebidas", "drinks", R.drawable.general_bebidas, Color(0xFF00D5E3)),
    Category("Frutas y Verduras", "fruits_vegetables",R.drawable.general_frutasyverduras, Color(0xFFF900FF)),
    Category("Carnes", "meats_sausages", R.drawable.general_carnes, Color(0xFFFFC700)),
    Category("Lácteos", "dairy_eggs", R.drawable.general_lacteos, Color(0xFF8B4513)),
    Category("Snacks", "snacks", R.drawable.general_snacks, Color(0xFF4CAF50)),
    Category("Panadería", "bakery", R.drawable.general_panaderia, Color(0xFFFF9800)),
    Category("Limpieza", "cleaning_home", R.drawable.general_limpieza, Color(0xFF2196F3)),
    Category("Abarrotes", "grains_groceries", R.drawable.general_abarrotes, Color(0xFF9C27B0)),
    Category("Emprendedores Locales", "Local_Emprend", R.drawable.general_emprendedores_locales,Color(0xFAD12387)) //imagen referencial, cambiar luego

)

val allProducts = listOf(
    // Carnes
    Product("c1", "Chuletas", "Chuleta de carne de cerdo, ideales para parrillas, guisos y recetas caseras.", "S/.16.90 x Kg", 3, R.drawable.carnes_chuletacerdo, "Carnes", "5%"),
    Product("c2", "Pollo Entero", "Pollo fresco y de granja, listo para hornear o trozar para tus guisos.", "S/.12.50 x Kg", 4, R.drawable.carnes_pollo, "Carnes"),
    Product("c3", "Bisteck de Res", "Finos cortes de res para freír, apanar o para un delicioso lomo saltado.", "S/.22.00 x Kg", 5, R.drawable.carnes_bisteck, "Carnes"),
    Product("c4", "Salchichas", "Pack de salchichas de la marca San Bernardo 10Ud, ideal para preparar hot dogs.", "S/.8.00", 3, R.drawable.carnes_hotdog, "Carnes", "5%"),
    Product("c5", "Carne Molida", "Carne molida fresca, versátil para preparar hamburguesas, guisos, albóndigas o rellenos caseros.", "S/. 14.50 x Kg", 5, R.drawable.carnes_carnemolida, "Carnes"),
    Product("c6", "Jamón", "Jamón tierno y listo para consumir, perfecto para sándwiches, desayunos, ensaladas o como complemento de tablas de fiambres.", "S/.20.00 x Kg", 5, R.drawable.carnes_jamon, "Carnes"),
    Product("c7", "Mortadela", "Fiambre embutido tipo mortadela, presentación de 200 g, textura suave y sabor característico.", "S/. 4.50", 4, R.drawable.carnes_mortadela, "Carnes"),
    Product("c8", "Salami", "Salami de la marca Induveca, embutido curado para corte en rebanadas.", "S/.6.00 x Ud", 5, R.drawable.carnes_salami, "Carnes"),
    Product("c9", "Chorizo", "Chorizos embutidos La Segoviana, presentados en paquete de 10 unidades, con sazón tradicional.", "S/.18.00", 5, R.drawable.carnes_chorizo, "Carnes"),
    // Bebidas
    Product("b1", "SMIRNOFF", "Vodka triplemente destilado y filtrado diez veces, conocido por su suavidad y versatilidad.", "S/.25.40", 5, R.drawable.bebidas_smirnoff, "Bebidas"),
    Product("b2", "Coca Cola 2.5L", "Bebida gaseosa refrescante para compartir en familia.", "S/.7.50", 5, R.drawable.bebidas_cocacola, "Bebidas"),
    Product("b3", "Jack Daniels", "Whiskey de Tennessee con un sabor suave y característico a roble y caramelo.", "S/.85.00", 5, R.drawable.bebidas_jack, "Bebidas"),
    Product("b4", "Black Label", "Whisky escocés de 12 años con sabor equilibrado, notas de frutas maduras, caramelo, vainilla y un toque ahumado", "S/.120.00", 5, R.drawable.bebidas_black, "Bebidas"),
    Product("b5", "Bacardi 750 ml", "Ron clásico, ligero y versátil, perfecto para cócteles, mezclas o momentos sociales.","S/.25.00" ,4, R.drawable.bebidas_bacardi, "Bebidas"),
    Product("b6", "Powerade", "Bebida rehidratante ideal para recuperar energía y electrolitos después del ejercicio o en días calurosos.", "S/.15.00", 3, R.drawable.bebidas_power, "Bebidas"),
    Product("b7", "360", "Bebida energizante “360”, en presentación de 300 ml, envase individual, sabor refrescante y contenido energético.", "S/.2.50", 4, R.drawable.bebidas_energizante, "Bebidas"),
    Product("b8", "Jugo", "Jugo Frugos del Valle sabor naranja, presentación de 1 litro, listo para consumir.", "S/.4.50", 4, R.drawable.bebidas_jugofrugos, "Bebidas"),
    Product("b9", "Fanta", "Refresco Fanta en presentación grande de 3 litros, gaseosa carbonatada, sabor cítrico", "S/.12.00", 3, R.drawable.bebidas_fanta, "Bebidas"),
    Product("b10", "Pulpin", "Pack de 24 unidades de bebida “Pulpín” de 315 ml sabor durazno, paquete al por mayor, práctico para distribución o venta al detalle.", "S/.14.50", 5, R.drawable.bebidas_pulpin, "Bebidas"),
    // Frutas y Verduras
    Product("fv1", "Manzana Roja", "Manzana roja de importación, dulce y crujiente, ideal para comer sola o en postres.", "S/.5.40 x Kg", 4, R.drawable.frutverd_manzana, "Frutas y Verduras"),
    Product("fv2", "Plátanos", "Platanos de seda, presentacion 0.50 kg aprox. Ricos en potasio y energía.", "S/.2.50", 3, R.drawable.frutverd_platano, "Frutas y Verduras", "15%"),
    Product("fv3", "Tomate", "Tomate Italiano fresco y jugoso, perfecto para ensaladas, salsas y guisos.", "S/.3.00 x Kg", 4, R.drawable.frutverd_tomate, "Frutas y Verduras"),
    Product("fv4", "Uvas Blancas", "Uvas blancas jugosas y dulces, perfectas para snack saludables, postres o acompañar comidas.", "S/.6.50 x Kg", 5, R.drawable.frutverd_uva, "Frutas y Verduras"),
    Product("fv5", "Cebolla", "Cebolla morada fresca, ideal para ensaladas, guisos y salsas, aporta sabor intenso y textura crujiente.", "S/.3.20 x Kg", 3, R.drawable.frutverd_cebolla, "Frutas y Verduras", "15%"),
    Product("fv6", "Papaya", "Papaya madura, rica en vitaminas y fibra, ideal para desayunos, jugos o postres naturales.", "S/.4.80 x Kg", 5, R.drawable.frutverd_papaya, "Frutas y Verduras"),
    Product("fv7", "Piña", "Piña fresca, fruto tropical, con pulpa jugosa y sabor dulce-ácido.", "S/. 3.00 x Ud" ,5,R.drawable.frutverd_pina, "Frutas y Verduras"),
    Product("fv8", "Limon", "Fruta cítrica, valorada por su acidez y aroma; usada tanto en cocina como bebida", "S/.2.40 x Kg", 5, R.drawable.frutverd_limon, "Frutas y Verduras"),
    Product("fv9", "Zanahoria", "Raíz comestible de textura firme y color naranja intenso, usada en múltiples preparaciones.", "S/.1.40 x Kg", 3, R.drawable.frutverd_zanahorias, "Frutas y Verduras", "15%"),
    Product("fv10", "Naranja", "Fruta cítrica con piel y pulpa jugosa, sabor dulce-ácido, buena para jugos o consumo directo.", "S/.2.50 x Kg", 5, R.drawable.frutverd_naranjas, "Frutas y Verduras"),
    // Abarrotes
    Product("a1", "Avena", "Avena tre ositos ,hojuelas de avena pre,cocida de grano entero, fuente de fibra y proteína para un desayuno saludable.", "S/.4.50", 4, R.drawable.abarrotes_avena, "Abarrotes", "25%"),
    Product("a2", "Arroz", "Arroz  M&K , ideal para acompañar todos tus platos. Bolsa de 750g.", "S/.4.50", 5, R.drawable.abarrotes_arroz, "Abarrotes"),
    Product("a3", "Aceite Primor", "Aceite vegetal de la marca Primor 1L, ideal para freír, cocinar o preparar ensaladas y aderezos.", "S/.8.00", 3, R.drawable.abarrotes_aceite, "Abarrotes", "25%"),
    Product("a4", "Atún Marinero", "Atún en lata “Marinero” de 170g, práctico para ensaladas, sánguches, pastas o comidas rápidas.", "S/.4.50", 5, R.drawable.abarrotes_atun, "Abarrotes"),
    Product("a5", "Azúcar Metro", "Azúcar común en presentación de 1 kg, indispensable para endulzar bebidas, postres y preparaciones de cocina.", "S/.3.20", 4, R.drawable.abarrotes_azucar, "Abarrotes", "25%"),
    Product("a6", "Aconcagua", "Duraznos en conserva de la marca Aconcagua, listos para postres, ensaladas de fruta o recetas dulces.", "S/.9.50", 5, R.drawable.abarrotes_aconcagua, "Abarrotes"),
    Product("a7", "Tallarín", "Tallarines de la marca Don Vittorio de 450g, pasta seca ideal para preparar pastas con salsas, sopas o guisos rápidos.", "S/.3.60", 4, R.drawable.abarrotes_tallarin, "Abarrotes"),
    Product("a8", "Cafe", "Café instantáneo en presentación de 200 g, marca NESCAFÉ Fina Selección.", "S/.28.50", 4, R.drawable.abarrotes_cafe, "Abarrotes", "25%"),
    Product("a9", "Lentejas", "Lentejas secas en bolsa de 500 g, legumbre básica para cocina.", "S/.4.50", 5, R.drawable.abarrotes_lentejas, "Abarrotes"),
    Product("a10", "Frijoles", "Frijol negro seco en empaque de 500 g, ideal como legumbre para diversos platos.", "S/.6.80", 4, R.drawable.abarrotes_frijoles, "Abarrotes"),
    // Lácteos
    Product("l1", "Leche Gloria", "Leche evaporada entera UHT, enriquecida con vitaminas A y D. Indispensable en tu cocina.", "S/.3.90", 5, R.drawable.lacteos_leche, "Lácteos"),
    Product("l2", "Yogurt Gloria", "Yogurt bebible con auténticos trozos de fruta, ideal para el desayuno o como un snack refrescante.", "S/.6.50", 4, R.drawable.lacteos_yogurt, "Lácteos"),
    Product("l3", "Huevos 3OUd", "Cartón con 30 huevos frescos, fuente de proteína ideal para tortillas, desayunos, repostería o comidas familiares.", "S/.25.00", 3, R.drawable.lacteos_huevos, "Lácteos"),
    Product("l4", "Queso fresco", "Queso fresco blanco, ideal para acompañar panes, preparar salsas, ensaladas o platos típicos peruanos.", "S/.18.00 x Kg", 5, R.drawable.lacteos_queso, "Lácteos"),
    Product("l5", "Condensada", "Leche condensada espesa y dulce, perfecta para postres, flanes, tortas, café o bebidas tradicionales.", "S/.7.50", 5, R.drawable.lacteos_lechecondensada, "Lácteos"),
    Product("l6", "Mantequilla", "Mantequilla cremosa ideal para cocinar, hornear panes/pasteles o para untar en desayunos y meriendas. En su presentacion en barra de 200g.", "S/.4.80", 5, R.drawable.lacteos_mantequilla, "Lácteos"),
    Product("l7", "Crema Batida", "Crema batida láctea en presentación de 250 g, con textura suave y consistente. Con un sabor fresco y equilibrado.", "S/.20.30", 5, R.drawable.lacteos_cremabatida, "Lácteos"),
    Product("l8", "Helado Cono", "Helado individual de 120 ml con sabor cappuccino y textura cremosa, combinando notas ligeras de café con un acabado dulce característico.", "S/.3.50", 4, R.drawable.lacteos_heladocono, "Lácteos"),
    Product("l9", "Helado Pote", "Helado en pote de 900 ml con crema sabor vainilla y chips de chocolate, ofreciendo textura suave.", "S/.11.90", 5, R.drawable.lacteos_heladopezi, "Lácteos"),
    Product("l10", "Crema Nata", "Nata líquida en envase de 200 ml con 18% de grasa, de consistencia ligera y sabor lácteo suave", "S/.10.50", 3, R.drawable.lacteos_cremanata, "Lácteos"),

    // Snacks
    Product("s1", "Cheetos", "Cheetos Flaming Hot 120 g con sabor picante, crujientes y con un toque intenso de especias, ideales como snack para acompañar películas o reuniones.", "S/.5.50", 5, R.drawable.snacks_cheetos, "Snacks", ),
    Product("s2", "Papas Lays", "Bolsa pequeña de papas fritas Lays, perfectas para picar entre comidas o acompañar salsas y bebidas.", "S/.2.00", 4, R.drawable.snacks_papaslays, "Snacks"),
    Product("s3", "Piqueos", "Mezcla de snacks tipo “piqueos”: pueden incluir mani, choclo tostado, palitos salados, etc. ideal como botana para compartir, de 200g.", "S/. 6.00", 5, R.drawable.snacks_piqueo, "Snacks"),
    Product("s4", "Ole Ole", "Snack dulce/esponjoso de la marca Ole Ole — buen acompañamiento para fiestas, reuniones o antojos rápidos.", "S/.4.80", 4, R.drawable.snacks_oleole, "Snacks", ),
    Product("s5", "Pringles ", "Lata de papas Pringles de 104g, perfectas para picar en reuniones, viajes o como snack durante el día.", "S/. 10.00", 5, R.drawable.snacks_pringles, "Snacks"),
    Product("s6", "Batimix", "Snack mixto “Batimix”, mezcla de granos de chocolate y yogurt de compañia con un sabor dulce.", "S/.3.50", 5, R.drawable.snacks_batimix, "Snacks"),
    Product("s7", "Chupetines", "Chupetines sabor tropical en bolsa de 456 g, con textura firme y centro dulce característico de la marca.", "S/. 8.00", 5, R.drawable.snacks_chupetines, "Snacks"),
    Product("s8", "Trident", "Chicle sin azúcar sabor tutti frutti en envase de 18 unidades, con textura suave y frescura prolongada.", "S/.4.50", 4, R.drawable.snacks_trident, "Snacks", ),
    Product("s9", "Doritos", "Snacks de maíz triangulares con textura crujiente y sabor intenso característico en cada variedad.", "S/. 2.00", 3, R.drawable.snacks_doritos, "Snacks"),
    Product("s10", "Takis", "Rollitos de maíz extrucrujientes de 90 g con sabor picante y toque ácido característico de la línea Fuego.", "S/.4.50", 3, R.drawable.snacks_takis, "Snacks"),

    // Limpieza
    Product("li1", "Clorox 1 galón", "Lavandina líquida de alta concentración, en envase de 1 galón, formulada para limpieza profunda y desinfección.", "S/.43.90 x Kg", 4, R.drawable.limpieza_clorox, "Limpieza", ),
    Product("li2", "Pasta dental", "Crema dental Advance White con agentes blanqueadores, textura suave y presentación en tubo estándar.", "S/.15.00 x Kg", 4, R.drawable.limpieza_pastadental, "Limpieza"),
    Product("li3", "Guantes", "Guantes elásticos y resistentes hechos de látex, diseñados para protección en tareas de limpieza y uso doméstico.", "S/.5.00 x Kg", 5, R.drawable.limpieza_guantes, "Limpieza"),
    Product("li4", "Kit Trapeador", "Trapeador con mecanismo giratorio de escurrido integrado y cubeta con sistema de pedal o presión para retirar el exceso de agua.", "S/.45.00", 4, R.drawable.limpieza_trapeador, "Limpieza", ),
    Product("li5", "Limpia vidrios Sapolio", "Solución líquida transparente formulada para remover manchas de vidrio, con acabado sin residuos.", "S/. 10.00", 5, R.drawable.limpieza_limpiavidrios, "Limpieza"),
    Product("li6", "Shampoo H&S 375 ml", "Shampoo anticaspa en presentación de 375 ml, con textura cremosa y fragancia fresca.", "S/.17.50 x Kg", 5, R.drawable.limpieza_shampoo, "Limpieza"),
    Product("li7", "PH ", "Papel higiénico doble hoja de 15 metros por rollo en paquete de 20 unidades, con textura suave y resistente", "S/.27.90", 5, R.drawable.limpieza_papelhigienido, "Limpieza"),
    Product("li8", "Detergente", "Detergente en polvo de 9 kg con fórmula de limpieza profunda y fragancia floral concentrada.", "S/.78.90", 4, R.drawable.limpieza_detergente, "Limpieza", ),
    Product("li9", "Quitamanchas", "Quitamanchas Sapolio líquido de 1800 ml con acción intensiva para remover suciedad difícil en prendas y superficies.", "S/. 35.00", 5, R.drawable.limpieza_quitamanchas, "Limpieza"),
    Product("li10", "Escoba", "Escoba de cerdas anchas y diseño robusto ideal para barrer áreas amplias con alta eficiencia.", "S/.13.90", 5, R.drawable.limpieza_escoba, "Limpieza"),

    // Panaderia
    Product("p1", "Pan de pulque", "Pan artesanal tradicional elaborado con masa fermentada con pulque, con textura blanda y corteza suave.", "S/.0.90 x Ud", 3, R.drawable.panaderia_panpulque, "Panadería", ),
    Product("p2", "Pan de molde", "Pan pre-envasado, rebanado y empacado, con miga uniforme de la marca Bimbo.", "S/. 9.50", 5, R.drawable.panaderia_panmolde, "Panadería"),
    Product("p3", "Pan cuernitos", "Pan pequeño de forma curvada (“cuernito”), masa suave y corteza ligera — típico de panaderías.", "S/. 0.40 x Ud", 5, R.drawable.panaderia_pancuernitos, "Panadería"),
    Product("p4", "Harina ", " Harina blanca Blanca Flor 1kg de trigo refinada, en polvo, para preparación de panes, repostería y masas.", "S/.6.00 x Kg", 4, R.drawable.panaderia_harina, "Panadería", ),
    Product("p5", "Pan integral", "Pan hecho con harina integral, miga más densa y corteza más rústica.", "S/. 5.50 x Kg", 5, R.drawable.panaderia_panintegral, "Panadería"),
    Product("p6", "Levadura", "Polvo leudante (gasificantes) para masas, mezcla en seco con harina  viene en sobres o envase compacto (Royal).", "S/. 3.00", 3, R.drawable.panaderia_levadura, "Panadería"),
    Product("p7", "Pan frances", "Pan de corteza dorada y crujiente con miga suave y ligera, elaborado en porciones individuales tradicionales.", "S/. 0.45 x Ud", 5, R.drawable.panaderia_panfrances, "Panadería"),
    Product("p8", "Roles Canela", "Pan dulce esponjoso enrollado con relleno de canela y azúcar, con textura suave y aroma característico.", "S/.2.80", 4, R.drawable.panaderia_rolesdecanela, "Panadería", ),

    //Emprendedores Locales
    Product("EmpLoc1","Cafe Cola", "Bebida local con un sabor agradable y refrescante","S/. 2.50",4,R.drawable.emprendedor_cafecola,"Emprendedores Locales")

    )
