package com.example.negolatina

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    init {
        _products.value = allProducts
    }
    fun getProductById(productId: String): Product? {
        return _products.value.find { it.id == productId }
    }

    fun rateProduct(productId: String, rating: Int) {
        val userId = auth.currentUser?.uid ?: return

        val ratingDocRef = db.collection("user_ratings").document("${userId}_${productId}")

        val ratingData = hashMapOf(
            "userId" to userId,
            "productId" to productId,
            "rating" to rating,
            "timestamp" to System.currentTimeMillis()
        )

        ratingDocRef.set(ratingData)
            .addOnSuccessListener {
                Log.d("ProductViewModel", "Puntuación guardada con éxito: $rating estrellas para $productId")
            }
            .addOnFailureListener { e ->
                Log.e("ProductViewModel", "Error al guardar la puntuación", e)
            }
    }


    fun addProduct(product: Product) {
        _products.value = _products.value + product
        Log.d("ProductViewModel", "Producto añadido (local): ${product.title}")
    }

    fun updateProduct(updatedProduct: Product) {
        _products.value = _products.value.map {
            if (it.id == updatedProduct.id) updatedProduct else it
        }
        Log.d("ProductViewModel", "Producto actualizado (local): ${updatedProduct.title}")
    }

    fun deleteProduct(productId: String) {
        _products.value = _products.value.filter { it.id != productId }
        Log.d("ProductViewModel", "Producto eliminado (local): $productId")
    }
}
