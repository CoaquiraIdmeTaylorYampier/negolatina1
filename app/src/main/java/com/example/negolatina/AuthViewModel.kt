package com.example.negolatina

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// estados de registro
sealed class RegistrationState {
    object Idle : RegistrationState()
    object Loading : RegistrationState()
    object Success : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}

// estados de login
sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    // obtener instancia
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    // Logica de registro
    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.Idle)
    val registrationState: StateFlow<RegistrationState> = _registrationState

    fun registerUser(email: String, pass: String, fullname: String) {
        if (email.isEmpty() || pass.isEmpty() || fullname.isEmpty()) {
            _registrationState.value = RegistrationState.Error("Todos los campos son obligatorios.")
            return
        }

        _registrationState.value = RegistrationState.Loading

        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = auth.currentUser
                    firebaseUser?.let {
                        saveUserProfile(it.uid, fullname, email)
                    } ?: run {
                        _registrationState.value = RegistrationState.Error("No se pudo obtener el usuario recién creado.")
                    }
                } else {
                    val errorMessage = task.exception?.message ?: "Ocurrió un error desconocido."
                    _registrationState.value = RegistrationState.Error(errorMessage)
                }
            }
    }

    private fun saveUserProfile(uid: String, fullname: String, email: String) {
        val userProfile = hashMapOf(
            "fullname" to fullname,
            "email" to email,
            "address" to "",
            "avatar_id" to R.drawable.logo_pollito
        )

        db.collection("users").document(uid)
            .set(userProfile)
            .addOnSuccessListener {
                _registrationState.value = RegistrationState.Success
            }
            .addOnFailureListener { e ->
                Log.e("AuthViewModel", "Error al guardar perfil: ", e)
                _registrationState.value = RegistrationState.Error("Error al guardar los datos del perfil.")
            }
    }

    fun resetRegistrationState() {
        _registrationState.value = RegistrationState.Idle
    }

    // logica de login
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun loginUser(email: String, pass: String) {
        if (email.isEmpty() || pass.isEmpty()) {
            _loginState.value = LoginState.Error("El correo y la contraseña no pueden estar vacíos.")
            return
        }
        _loginState.value = LoginState.Loading
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginState.value = LoginState.Success
                } else {
                    val errorMessage = task.exception?.message ?: "Credenciales incorrectas."
                    _loginState.value = LoginState.Error(errorMessage)
                }
            }
    }

    fun resetLoginState() {
        _loginState.value = LoginState.Idle
    }
}
