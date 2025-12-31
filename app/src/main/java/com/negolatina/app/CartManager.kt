package com.negolatina.app

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class CartItem(
    val product: Product,
    var quantity: Int = 1
) {
    val subtotal: Double
        get() {
            val priceString = product.price
                .replace("S/.", "")
                .replace("S/", "") 
                .replace("x Kg", "")
                .replace("x Kg.", "")
                .replace("x Ud", "")
                .trim()

            return try {
                val price = priceString.toDoubleOrNull() ?: 0.0
                price * quantity
            } catch (e: Exception) {
                0.0
            }
        }
}

/**
 * Gestor global del carrito de compras
 * Singleton para mantener el estado del carrito en toda la app
 */
object CartManager {

    // Usamos SnapshotStateList para que Compose detecte cambios en la lista (agregar, quitar, reemplazar)
    private val _items: SnapshotStateList<CartItem> = mutableStateListOf()
    val items: List<CartItem> get() = _items

    // Cantidad total de items
    val itemCount: Int
        get() = _items.sumOf { it.quantity }

    // Total a pagar
    val total: Double
        get() = _items.sumOf { it.subtotal }

    fun addProduct(product: Product, quantity: Int = 1) {
        val index = _items.indexOfFirst { it.product.id == product.id }

        if (index != -1) {
            // Si ya existe, REEMPLAZAMOS el item con una copia actualizada.
            val existingItem = _items[index]
            _items[index] = existingItem.copy(quantity = existingItem.quantity + quantity)
        } else {
            // Si no existe, agregar nuevo item
            _items.add(CartItem(product, quantity))
        }
    }

    fun removeProduct(productId: String) {
        _items.removeAll { it.product.id == productId }
    }

    fun updateQuantity(productId: String, newQuantity: Int) {
        if (newQuantity <= 0) {
            removeProduct(productId)
            return
        }

        val index = _items.indexOfFirst { it.product.id == productId }
        if (index != -1) {
            val item = _items[index]
            _items[index] = item.copy(quantity = newQuantity)
        }
    }
    fun incrementQuantity(productId: String) {
        val index = _items.indexOfFirst { it.product.id == productId }
        if (index != -1) {
            val item = _items[index]
            _items[index] = item.copy(quantity = item.quantity + 1)
        }
    }

    /**
     * Decrementar cantidad de un producto
     */
    fun decrementQuantity(productId: String) {
        val index = _items.indexOfFirst { it.product.id == productId }
        if (index != -1) {
            val item = _items[index]
            if (item.quantity > 1) {
                _items[index] = item.copy(quantity = item.quantity - 1)
            } else {
                _items.removeAt(index)
            }
        }
    }

    /**
     * Limpiar todo el carrito
     */
    fun clear() {
        _items.clear()
    }

    /**
     * Verificar si el carrito está vacío
     */
    fun isEmpty(): Boolean = _items.isEmpty()

    /**
     * Verificar si un producto está en el carrito
     */
    fun contains(productId: String): Boolean {
        return _items.any { it.product.id == productId }
    }

    /**
     * Obtener cantidad de un producto en el carrito
     */
    fun getQuantity(productId: String): Int {
        return _items.find { it.product.id == productId }?.quantity ?: 0
    }
}
