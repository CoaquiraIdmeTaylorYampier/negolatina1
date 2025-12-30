package com.negolatina.app

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {
    

    val _isEcoModeEnabled = mutableStateOf(false)
    val isEcoModeEnabled: State<Boolean> = _isEcoModeEnabled

    fun toggleEcoMode(enabled: Boolean) {
        _isEcoModeEnabled.value = enabled

    }
}
