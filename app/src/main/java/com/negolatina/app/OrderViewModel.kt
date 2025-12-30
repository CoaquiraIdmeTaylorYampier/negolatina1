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

class OrderViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val ordersCollection = db.collection("orders")
    private val productsCollection = db.collection("products")
    private val usersCollection = db.collection("users") // Referencia a usuarios

    private var listener: ListenerRegistration? = null

    // Para el admin: todos los pedidos
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders.asStateFlow()

    // Para el usuario: solo sus pedidos
    private val _userOrders = MutableStateFlow<List<Order>>(emptyList())
    val userOrders: StateFlow<List<Order>> = _userOrders.asStateFlow()

    // Mapa para "traducir" userId a nombre
    private val _userNames = MutableStateFlow<Map<String, String>>(emptyMap())
    val userNames: StateFlow<Map<String, String>> = _userNames.asStateFlow()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        usersCollection.get().addOnSuccessListener { snapshot ->
            val userMap = snapshot.documents.associate {
                it.id to (it.getString("fullname") ?: "Usuario Desconocido")
            }
            _userNames.value = userMap
        }
    }

    fun listenToAllOrders() {
        stopListening()
        listener = ordersCollection
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w("OrderViewModel", "Error al escuchar todos los pedidos.", e)
                    return@addSnapshotListener
                }
                _orders.value = snapshots?.mapNotNull { it.toObject<Order>() } ?: emptyList()
            }
    }

    fun listenToUserOrders() {
        stopListening()
        val userId = auth.currentUser?.uid
        if (userId == null) {
            _userOrders.value = emptyList()
            return
        }

        listener = ordersCollection
            .whereEqualTo("userId", userId)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w("OrderViewModel", "Error al escuchar pedidos del usuario.", e)
                    return@addSnapshotListener
                }
                _userOrders.value = snapshots?.mapNotNull { it.toObject<Order>() } ?: emptyList()
            }
    }

    fun acceptOrder(order: Order) {
        val batch = db.batch()

        order.items.forEach { item ->
            val productRef = productsCollection.document(item.productId)
            val decrement = -item.quantity.toLong()
            batch.update(productRef, "cantidad", com.google.firebase.firestore.FieldValue.increment(decrement))
        }

        val orderRef = ordersCollection.document(order.id)
        batch.update(orderRef, "status", OrderStatus.ACCEPTED.name)

        batch.commit()
            .addOnSuccessListener { Log.d("OrderViewModel", "Pedido ${order.id} aceptado y stock actualizado.") }
            .addOnFailureListener { e -> Log.e("OrderViewModel", "Error al aceptar pedido ${order.id}", e) }
    }

    fun rejectOrder(orderId: String) {
        ordersCollection.document(orderId)
            .update("status", OrderStatus.REJECTED.name)
            .addOnSuccessListener { Log.d("OrderViewModel", "Pedido $orderId rechazado.") }
            .addOnFailureListener { e -> Log.e("OrderViewModel", "Error al rechazar pedido $orderId", e) }
    }

    override fun onCleared() {
        stopListening()
        super.onCleared()
    }

    fun stopListening() {
        listener?.remove()
    }
}