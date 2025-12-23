package com.example.negolatina

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductViewModel : ViewModel() {

    // 1. Conexión a los servicios de Firebase
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    // StateFlow para mantener la lista de productos
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    init {
        // Cargar los productos iniciales de la lista estática en Data.kt
        _products.value = allProducts
    }

    /**
     * Busca un producto por su ID.
     * @param productId El ID del producto a buscar.
     * @return El objeto Product si se encuentra, o null si no.
     */
    fun getProductById(productId: String): Product? {
        return _products.value.find { it.id == productId }
    }

    /**
     * Guarda o actualiza la puntuación que un usuario le da a un producto.
     * @param productId El ID del producto que se está puntuando.
     * @param rating La puntuación (número de estrellas) que el usuario ha dado.
     */
    fun rateProduct(productId: String, rating: Int) {
        // Obtenemos el ID del usuario que ha iniciado sesión. Si no hay nadie, no hacemos nada.
        val userId = auth.currentUser?.uid ?: return

        // Creamos un "documento" único para este voto específico.
        // La clave será una combinación del ID de usuario y el ID del producto.
        // Esto asegura que un usuario solo puede tener un voto por producto.
        val ratingDocRef = db.collection("user_ratings").document("${userId}_${productId}")

        // Creamos un mapa con la información que queremos guardar.
        val ratingData = hashMapOf(
            "userId" to userId,
            "productId" to productId,
            "rating" to rating,
            "timestamp" to System.currentTimeMillis() // Guardamos la fecha para futuras analíticas
        )

        // Guardamos los datos en Firestore.
        // .set() creará el documento si no existe, o lo sobrescribirá si ya existía.
        ratingDocRef.set(ratingData)
            .addOnSuccessListener {
                Log.d("ProductViewModel", "Puntuación guardada con éxito: $rating estrellas para $productId")
            }
            .addOnFailureListener { e ->
                Log.e("ProductViewModel", "Error al guardar la puntuación", e)
            }
    }

    // --- Funciones de gestión de productos ---

    fun addProduct(product: Product) {
        // Por ahora, solo actualizamos la lista local.
        // Más adelante, esto se actualizará para añadir a Firestore.
        _products.value = _products.value + product
        Log.d("ProductViewModel", "Producto añadido: ${product.title}")
    }

    fun updateProduct(updatedProduct: Product) {
        // Por ahora, solo actualizamos la lista local.
        _products.value = _products.value.map {
            if (it.id == updatedProduct.id) updatedProduct else it
        }
        Log.d("ProductViewModel", "Producto actualizado: ${updatedProduct.title}")
    }

    fun deleteProduct(productId: String) {
        // Por ahora, solo actualizamos la lista local.
        _products.value = _products.value.filter { it.id != productId }
        Log.d("ProductViewModel", "Producto eliminado: $productId")
    }

    // Aquí, en el futuro, podríamos añadir funciones como:
    // fun loadUserRatingForProduct(productId: String) { ... }
    // fun getAverageRatingForProduct(productId: String) { ... }
}
