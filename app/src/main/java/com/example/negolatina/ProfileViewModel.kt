package com.example.negolatina

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileViewModel : ViewModel() {

    // conectamos a Firebase
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    // Estados de perfil
    private val _name = mutableStateOf("Cargando...")
    val name: State<String> = _name

    private val _email = mutableStateOf("...")
    val email: State<String> = _email

    private val _address = mutableStateOf("...")
    val address: State<String> = _address

    private val _profileImageRes = mutableIntStateOf(R.drawable.avatar_de_usuario_)
    val profileImageRes: State<Int> = _profileImageRes

    init {
        loadUserProfile()
    }

    // caragar datos del usuario desde Firebase
    private fun loadUserProfile() {
        val firebaseUser = auth.currentUser
        if (firebaseUser == null) {
            _name.value = "Invitado"
            _email.value = ""
            _address.value = ""
            return
        }

        db.collection("users").document(firebaseUser.uid).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    _name.value = document.getString("fullname") ?: "Sin nombre"
                    _email.value = document.getString("email") ?: "Sin correo"
                    _address.value = document.getString("address") ?: ""
                    _profileImageRes.intValue = (document.getLong("avatar_id") ?: R.drawable.avatar_de_usuario_).toInt()
                } else {
                    Log.d("ProfileViewModel", "No se encontró el documento del perfil")
                    _name.value = "Perfil no encontrado"
                }
            }
            .addOnFailureListener { exception ->
                Log.d("ProfileViewModel", "Error al obtener perfil: ", exception)
                _name.value = "Error al cargar"
            }
    }

    // actualizamos perfil si se realiza
    fun updateProfile(newName: String, newEmail: String, newAddress: String) {
        _name.value = newName
        _email.value = newEmail
        _address.value = newAddress
        
        val firebaseUser = auth.currentUser ?: return

        val updatedData = mapOf(
            "fullname" to newName,
            "email" to newEmail,
            "address" to newAddress
        )

        db.collection("users").document(firebaseUser.uid)
            .update(updatedData)
            .addOnSuccessListener { Log.d("ProfileViewModel", "Perfil actualizado en Firestore!") }
            .addOnFailureListener { e -> Log.w("ProfileViewModel", "Error al actualizar perfil", e) }
    }

    fun setProfilePicture(newImageRes: Int) {
        _profileImageRes.intValue = newImageRes
        
        val firebaseUser = auth.currentUser ?: return
        val updatedData = mapOf("avatar_id" to newImageRes)

        db.collection("users").document(firebaseUser.uid)
            .update(updatedData)
            .addOnSuccessListener { Log.d("ProfileViewModel", "Avatar actualizado en Firestore!") }
            .addOnFailureListener { e -> Log.w("ProfileViewModel", "Error al actualizar avatar", e) }
    }

    fun getAvatarList(): List<Int> {
        return listOf(
            R.drawable.avatar_de_chica,
            R.drawable.avatar_de_chica_con_estilo,
            R.drawable.avatar_de_chica_genial,
            R.drawable.avatar_de_chico_amigable,
            R.drawable.avatar_de_chico_fitness,
            R.drawable.avatar_de_chico_genial,
            R.drawable.avatar_de_chico_relajado,
            R.drawable.avatar_de_desarrollador,
            R.drawable.avatar_de_dise_ador,
            R.drawable.avatar_de_empresaria,
            R.drawable.avatar_de_hombre_de_finanzas,
            R.drawable.avatar_de_mujer_profesional,
            R.drawable.avatar_de_pensador_profundo,
            R.drawable.avatar_de_profesor,
            R.drawable.avatar_de_usuario_,
            R.drawable.avatar_femenino,
            R.drawable.avatar_masculino,
            R.drawable.avatar_viajero
        )
    }
}
