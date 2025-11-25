package com.example.negolatina

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProductViewModel : ViewModel() {

    // 1. Conexión a los servicios de Firebase
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

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

    // Aquí, en el futuro, podríamos añadir funciones como:
    // fun loadUserRatingForProduct(productId: String) { ... }
    // fun getAverageRatingForProduct(productId: String) { ... }
}
