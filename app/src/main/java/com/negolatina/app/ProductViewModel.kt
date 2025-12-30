package com.negolatina.app

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
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
    private val ordersCollection = db.collection("orders") // Nueva colección
    private val auth = FirebaseAuth.getInstance()

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    private var snapshotListener: ListenerRegistration? = null

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
            startListeningToProducts()
        } else {
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
                    doc.toObject<Product>().copy(id = doc.id)
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
                productsCollection.document(staticProduct.id).set(staticProduct)
                    .addOnFailureListener { e -> Log.e("ProductViewModel", "Fallo al migrar ${staticProduct.title}", e) }
            }
        }
    }

    fun getProductById(productId: String): Product? {
        return _products.value.find { it.id == productId }
    }

    fun createOrder(cartItems: List<CartItem>, address: String, total: Double) {
        val currentUser = auth.currentUser ?: return
        val orderId = ordersCollection.document().id

        val orderItems = cartItems.map { item ->
            OrderItem(
                productId = item.product.id,
                title = item.product.title,
                quantity = item.quantity
            )
        }

        val order = Order(
            id = orderId,
            userId = currentUser.uid,
            items = orderItems,
            address = address,
            total = total,
            timestamp = System.currentTimeMillis(),
            status = OrderStatus.PENDING.name
        )

        ordersCollection.document(orderId).set(order)
            .addOnSuccessListener { Log.d("ProductViewModel", "Pedido creado con éxito: $orderId") }
            .addOnFailureListener { e -> Log.e("ProductViewModel", "Error al crear el pedido", e) }
    }

    fun addProduct(product: Product) {
        val prefix = categoryPrefixes[product.category]

        if (prefix == null) {
            Log.e("ProductViewModel", "Categoría desconocida: ${product.category}, se usará ID automático.")
            val docRef = productsCollection.document()
            docRef.set(product.copy(id = docRef.id))
            return
        }

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

                val finalProduct = product.copy(id = newId)
                productsCollection.document(newId).set(finalProduct)
            }
    }


    fun updateProduct(product: Product) {
        if (product.id.isEmpty()) return
        productsCollection.document(product.id).set(product)
    }

    fun deleteProduct(productId: String) {
        if (productId.isEmpty()) return
        productsCollection.document(productId).delete()
    }

    fun purchaseProducts(cartItems: List<CartItem>) {
        val batch = db.batch()
        cartItems.forEach { item ->
            val productRef = productsCollection.document(item.product.id)
            val newQuantity = item.product.cantidad - item.quantity
            batch.update(productRef, "cantidad", newQuantity)
        }
        batch.commit()
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
    }
}