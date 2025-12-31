package com.negolatina.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

data class Promotion(val imageRes: Int)
val promotions = listOf(
    Promotion(R.drawable.banner_oferta),
    Promotion(R.drawable.banner_carne)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun HomeScreen(navController: NavController, profileViewModel: ProfileViewModel, productViewModel: ProductViewModel = viewModel()) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val products by productViewModel.products.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Box(modifier = Modifier.fillMaxWidth(0.8f)) {
                    ClientNavigationDrawer(
                        navController = navController,
                        closeDrawer = { scope.launch { drawerState.close() } },
                        profileViewModel = profileViewModel
                    )
                }
            }
        },
        gesturesEnabled = true
    ) {
        Scaffold { paddingValues ->
            HomeScreenContent(
                navController = navController,
                products = products,
                paddingValues = paddingValues,
                onMenuClick = { scope.launch { drawerState.open() } }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    navController: NavController,
    products: List<Product>,
    paddingValues: PaddingValues,
    onMenuClick: () -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    val filteredProducts = if (searchText.isBlank()) {
        products
    } else {
        products.filter { 
            it.title.contains(searchText, ignoreCase = true) || 
            it.description.contains(searchText, ignoreCase = true)
        }
    }

    val productsWithDiscount = filteredProducts.filter { it.discount != null }
    val categoriesForHome = allCategories.take(3)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(paddingValues)
    ) {
        // BAR
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE31E24))
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onMenuClick) {
                Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
            }
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Buscar") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                shape = RoundedCornerShape(50),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            IconButton(onClick = { navController.navigate("shopping_cart") }) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Shopping Cart", tint = Color.White)
            }
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            if (searchText.isNotBlank()) {
                item {
                    Text(
                        text = "Resultados de búsqueda:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                
                if (filteredProducts.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("No se encontraron productos.", color = Color.Gray)
                        }
                    }
                } else {
                    items(filteredProducts) { product ->
                        ProductItemRow(product = product, navController = navController)
                    }
                }
            } else {
                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(promotions) { promotion ->
                            Image(
                                painter = painterResource(id = promotion.imageRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(150.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

                item {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Categorias", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Ver mas", fontSize = 14.sp, color = Color.Gray,
                                modifier = Modifier.clickable { navController.navigate("all_categories") })
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            items(categoriesForHome) { category ->
                                CategoryCard(category = category, navController = navController)
                            }
                        }
                    }
                }

                item {
                    Column(modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Productos en Oferta", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Ver mas", fontSize = 14.sp, color = Color.Gray,
                                modifier = Modifier.clickable { navController.navigate("all_products") })
                        }
                    }
                }

                items(productsWithDiscount) { product ->
                    ProductItemRow(product = product, navController = navController)
                }
            }
        }
    }
}

@Composable
fun CategoryCard(category: Category, navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { navController.navigate(category.route) }
    ) {
        Card(
            modifier = Modifier.size(120.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = category.color)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = category.imageRes),
                    contentDescription = category.name,
                    modifier = Modifier.size(105.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = category.name, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun ProductItemRow(product: Product, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { navController.navigate("product/${product.id}") },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val imageToShow = if (product.imageRes != 0) product.imageRes else R.drawable.logo_pollito

        if (product.imageUri != null) {
            AsyncImage(
                model = product.imageUri,
                contentDescription = product.title,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(id = imageToShow),
                contentDescription = product.title,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = product.title, fontWeight = FontWeight.Bold)
            Text(text = product.description, fontSize = 14.sp, color = Color.Gray, maxLines = 1)
        }
        Spacer(modifier = Modifier.width(16.dp))
        product.discount?.let {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color(0xFFE31E24), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = it, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    Scaffold {
        HomeScreenContent(
            navController = rememberNavController(),
            products = allProducts,
            paddingValues = it,
            onMenuClick = {}
        )
    }
}
