package com.example.negolatina

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {
    
    // Cambiado a public para que sea accesible desde otros composables
    val _isEcoModeEnabled = mutableStateOf(false)
    val isEcoModeEnabled: State<Boolean> = _isEcoModeEnabled

    fun toggleEcoMode(enabled: Boolean) {
        _isEcoModeEnabled.value = enabled
        // Aquí podrías agregar lógica para guardar esta preferencia en SharedPreferences o DataStore
        // para que persista al cerrar la app.
    }
}
