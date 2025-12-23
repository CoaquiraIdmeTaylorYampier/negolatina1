package com.negolatina.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.negolatina.app.ui.theme.NegolatinaTheme

// ============================================
// GESTOR DE FAVORITOS (FavoritesManager)
// ============================================
object FavoritesManager {

    private val _favorites: SnapshotStateList<Product> = mutableStateListOf()
    val favorites: List<Product> get() = _favorites

    val count: Int get() = _favorites.size

    fun addFavorite(product: Product) {
        if (!isFavorite(product.id)) {
            _favorites.add(product)
        }
    }

    fun removeFavorite(productId: String) {
        _favorites.removeAll { it.id == productId }
    }

    fun toggleFavorite(product: Product) {
        if (isFavorite(product.id)) {
            removeFavorite(product.id)
        } else {
            addFavorite(product)
        }
    }

    fun isFavorite(productId: String): Boolean {
        return _favorites.any { it.id == productId }
    }

    fun clear() {
        _favorites.clear()
    }

    fun isEmpty(): Boolean = _favorites.isEmpty()
}

// ============================================
// PANTALLA DE FAVORITOS (FavoritesScreen)
// ============================================
@Preview(showBackground = true, name = "Pantalla de Favoritos")
@Composable
fun FavoritesScreenPreview() {
    NegolatinaTheme {
        FavoritesScreen(navController = rememberNavController())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController: NavController) {
    val favoriteProducts = FavoritesManager.favorites
    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            snackbarHostState.showSnackbar(
                message = snackbarMessage,
                duration = SnackbarDuration.Short
            )
            showSnackbar = false
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Mis favoritos",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("search") }) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_search),
                            contentDescription = "Buscar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFE31E24)
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            if (favoriteProducts.isEmpty()) {
                // Vista vacía
                EmptyFavoritesView(navController)
            } else {
                // Lista de favoritos
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(favoriteProducts, key = { it.id }) { product ->
                        FavoriteItemCard(
                            product = product,
                            onRemove = {
                                FavoritesManager.removeFavorite(product.id)
                                snackbarMessage = "${product.title} eliminado de favoritos"
                                showSnackbar = true
                            },
                            onAddToCart = {
                                CartManager.addProduct(product, 1)
                                snackbarMessage = "${product.title} agregado al carrito"
                                showSnackbar = true
                            },
                            onClick = {
                                navController.navigate("product/${product.id}")
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteItemCard(
    product: Product,
    onRemove: () -> Unit,
    onAddToCart: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del producto
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF5F5F5)),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Información del producto
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF333333),
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.description,
                    fontSize = 14.sp,
                    color = Color(0xFF666666),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = product.price,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFFE31E24)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Botones de acción
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Botón eliminar
                IconButton(
                    onClick = onRemove,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Eliminar de favoritos",
                        tint = Color(0xFFE31E24),
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Botón agregar al carrito
                IconButton(
                    onClick = onAddToCart,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Agregar al carrito",
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyFavoritesView(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            modifier = Modifier.size(120.dp),
            tint = Color(0xFFE0E0E0)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "No tienes favoritos aún",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Agrega productos a tus favoritos para verlos aquí",
            fontSize = 14.sp,
            color = Color(0xFF666666),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { navController.navigate("home") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE31E24)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                "Explorar productos",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}