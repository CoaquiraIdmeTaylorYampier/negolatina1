package com.example.negolatina

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

/**
 * Item del carrito con cantidad
 */
data class CartItem(
    val product: Product,
    var quantity: Int = 1
) {
    val subtotal: Double
        get() {
            // Extraer el precio numérico del string "S/. XX.XX x Kg"
            val priceString = product.price
                .replace("S/.", "")
                .replace("x Kg", "")
                .replace("x Kg.", "")
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

    // Lista observable de items en el carrito
    private val _items: SnapshotStateList<CartItem> = mutableStateListOf()
    val items: List<CartItem> get() = _items

    // Cantidad total de items
    val itemCount: Int
        get() = _items.sumOf { it.quantity }

    // Total a pagar
    val total: Double
        get() = _items.sumOf { it.subtotal }

    /**
     * Agregar producto al carrito
     * Si ya existe, aumenta la cantidad
     */
    fun addProduct(product: Product, quantity: Int = 1) {
        val existingItem = _items.find { it.product.id == product.id }

        if (existingItem != null) {
            // Si ya existe, aumentar cantidad
            existingItem.quantity += quantity
        } else {
            // Si no existe, agregar nuevo item
            _items.add(CartItem(product, quantity))
        }
    }

    /**
     * Eliminar producto del carrito
     */
    fun removeProduct(productId: String) {
        _items.removeAll { it.product.id == productId }
    }

    /**
     * Actualizar cantidad de un producto
     */
    fun updateQuantity(productId: String, newQuantity: Int) {
        if (newQuantity <= 0) {
            removeProduct(productId)
            return
        }

        val item = _items.find { it.product.id == productId }
        item?.quantity = newQuantity
    }

    /**
     * Incrementar cantidad de un producto
     */
    fun incrementQuantity(productId: String) {
        val item = _items.find { it.product.id == productId }
        item?.quantity = (item.quantity + 1)
    }

    /**
     * Decrementar cantidad de un producto
     */
    fun decrementQuantity(productId: String) {
        val item = _items.find { it.product.id == productId }
        if (item != null) {
            if (item.quantity > 1) {
                item.quantity -= 1
            } else {
                removeProduct(productId)
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