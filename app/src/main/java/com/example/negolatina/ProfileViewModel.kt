package com.example.negolatina

import androidx.annotation.DrawableRes
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    val name = mutableStateOf("usuario")
    val email = mutableStateOf("usuario@gmail.com")
    val address = mutableStateOf("Avenida Siempre Viva 123")

    private val avatars = listOf(
        R.drawable.avatar_viajero,
        R.drawable.avatar_femenino,
        R.drawable.avatar_masculino,
        R.drawable.avatar_de_chica,
        R.drawable.avatar_de_profesor,
        R.drawable.avatar_de_chico_amigable,
        R.drawable.avatar_de_chico_fitness,
        R.drawable.avatar_de_chico_relajado,
        R.drawable.avatar_de_chica_genial,
        R.drawable.avatar_de_chica_con_estilo,
        R.drawable.avatar_de_chico_genial,
        R.drawable.avatar_de_desarrollador,
        R.drawable.avatar_de_dise_ador,
        R.drawable.avatar_de_empresaria,
        R.drawable.avatar_de_hombre_de_finanzas,
        R.drawable.avatar_de_mujer_profesional,
        R.drawable.avatar_de_pensador_profundo,
        R.drawable.avatar_de_usuario_,
        R.drawable.logo_pollito
    )

    val profileImageRes = mutableStateOf(avatars.first())

    fun getAvatarList(): List<Int> {
        return avatars
    }
    fun setProfilePicture(@DrawableRes avatarRes: Int) {
        if (avatars.contains(avatarRes)) {
            profileImageRes.value = avatarRes
        }
    }

    fun updateProfile(newName: String, newEmail: String, newAddress: String) {
        name.value = newName
        email.value = newEmail
        address.value = newAddress
    }
}
