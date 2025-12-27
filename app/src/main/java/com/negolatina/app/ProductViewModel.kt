package com.negolatina.app

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val productsCollection = db.collection("products")
    private val auth = FirebaseAuth.getInstance()

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    private var snapshotListener: ListenerRegistration? = null

    // Map for category prefixes
    private val categoryPrefixes = mapOf(
        "Bebidas" to "b",
        "Frutas y Verduras" to "fv",
        "Carnes" to "c",
        "Lácteos" to "l",
        "Snacks" to "s",
        "Panadería" to "p",
        "Limpieza" to "li",
        "Abarrotes" to "a",
        "Emprendedores Locales" to "EmpLoc"
    )

    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        val user = firebaseAuth.currentUser
        if (user != null) {
            Log.d("ProductViewModel", "Usuario detectado: ${user.uid}. Iniciando sincronización...")
            startListeningToProducts()
        } else {
            Log.d("ProductViewModel", "No hay usuario. Deteniendo sincronización.")
            stopListeningToProducts()
            _products.value = emptyList()
        }
    }

    init {
        auth.addAuthStateListener(authStateListener)
    }

    override fun onCleared() {
        super.onCleared()
        auth.removeAuthStateListener(authStateListener)
        stopListeningToProducts()
    }

    private fun startListeningToProducts() {
        if (snapshotListener != null) return

        snapshotListener = productsCollection.addSnapshotListener { snapshots, e ->
            if (e != null) {
                Log.w("ProductViewModel", "Error al escuchar productos.", e)
                return@addSnapshotListener
            }

            val productList = snapshots?.mapNotNull { doc ->
                try {
                    val product = doc.toObject<Product>()
                    product.copy(id = doc.id)
                } catch (e: Exception) {
                    Log.e("ProductViewModel", "Error al convertir producto: ${doc.id}", e)
                    null
                }
            } ?: emptyList()

            _products.value = productList

            if (allProducts.isNotEmpty()) {
                migrateMissingProducts(productList)
            }
        }
    }

    private fun stopListeningToProducts() {
        snapshotListener?.remove()
        snapshotListener = null
    }

    private fun migrateMissingProducts(currentFirestoreProducts: List<Product>) {
        val currentIds = currentFirestoreProducts.map { it.id }.toSet()

        allProducts.forEach { staticProduct ->
            if (!currentIds.contains(staticProduct.id)) {
                Log.d("ProductViewModel", "Subiendo producto faltante: ${staticProduct.title}")
                productsCollection.document(staticProduct.id).set(staticProduct)
                    .addOnFailureListener { e -> Log.e("ProductViewModel", "Fallo al migrar ${staticProduct.title}", e) }
            }
        }
    }

    fun getProductById(productId: String): Product? {
        return _products.value.find { it.id == productId }
    }

    fun addProduct(product: Product) {
        val prefix = categoryPrefixes[product.category]

        if (prefix == null) {
            Log.e("ProductViewModel", "Categoría desconocida: ${product.category}, se usará ID automático.")
            val docRef = productsCollection.document()
            docRef.set(product.copy(id = docRef.id))
                .addOnSuccessListener { Log.d("ProductViewModel", "Producto añadido con ID automático: ${product.title}") }
                .addOnFailureListener { e -> Log.e("ProductViewModel", "Error al añadir producto", e) }
            return
        }

        // Query for all products with this prefix to find the highest ID
        productsCollection
            .whereGreaterThanOrEqualTo("id", prefix)
            .whereLessThan("id", prefix + "\uf8ff")
            .get()
            .addOnSuccessListener { documents ->
                var maxNumericPart = 0
                documents.forEach { document ->
                    try {
                        val numericPart = document.id.removePrefix(prefix).toInt()
                        if (numericPart > maxNumericPart) {
                            maxNumericPart = numericPart
                        }
                    } catch (e: NumberFormatException) {
                        Log.w("ProductViewModel", "ID con formato no numérico ignorado: ${document.id}")
                    }
                }

                val newNumericPart = maxNumericPart + 1
                val newId = "$prefix$newNumericPart"

                // Save the product with the new custom ID
                val finalProduct = product.copy(id = newId)
                productsCollection.document(newId).set(finalProduct)
                    .addOnSuccessListener { Log.d("ProductViewModel", "Producto añadido: ${finalProduct.title} con ID: $newId") }
                    .addOnFailureListener { e -> Log.e("ProductViewModel", "Error al añadir producto con ID personalizado", e) }
            }
            .addOnFailureListener { exception ->
                Log.e("ProductViewModel", "Error al buscar productos para el prefijo $prefix. Se usará ID automático.", exception)
                 val docRef = productsCollection.document()
                 docRef.set(product.copy(id = docRef.id))
                    .addOnSuccessListener { Log.d("ProductViewModel", "Producto añadido con ID automático: ${product.title}") }
                    .addOnFailureListener { e -> Log.e("ProductViewModel", "Error al añadir producto", e) }
            }
    }


    fun updateProduct(product: Product) {
        if (product.id.isEmpty()) return
        productsCollection.document(product.id).set(product)
            .addOnSuccessListener { Log.d("ProductViewModel", "Producto actualizado") }
    }

    fun deleteProduct(productId: String) {
        if (productId.isEmpty()) return
        productsCollection.document(productId).delete()
            .addOnSuccessListener { Log.d("ProductViewModel", "Producto eliminado") }
    }

    fun purchaseProducts(cartItems: List<CartItem>) {
        val batch = db.batch()
        cartItems.forEach { item ->
            val productRef = productsCollection.document(item.product.id)
            val newQuantity = item.product.cantidad - item.quantity
            batch.update(productRef, "cantidad", newQuantity)
        }
        batch.commit()
            .addOnSuccessListener { Log.d("ProductViewModel", "Compra exitosa. Stock actualizado.") }
            .addOnFailureListener { e -> Log.e("ProductViewModel", "Error al actualizar stock", e) }
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
                val productRef = productsCollection.document(productId)
                db.runTransaction { transaction ->
                    transaction.update(productRef, "rating", rating)
                    null
                }
            }
    }
}