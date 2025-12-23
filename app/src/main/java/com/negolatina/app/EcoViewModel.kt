package com.negolatina.app
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import kotlin.math.pow

class EcoViewModel : ViewModel() {
    private val _currentLevel = mutableIntStateOf(1)
    val currentLevel: State<Int> = _currentLevel

    private val _currentPoints = mutableIntStateOf(0)
    val currentPoints: State<Int> = _currentPoints

    val pointsForNextLevel: Int
        get() = (10 * _currentLevel.intValue.toDouble().pow(1.5)).toInt()

    private val _treeProgress = mutableFloatStateOf(0f)
    val treeProgress: State<Float> = _treeProgress

    private val maxLevel = 15

    init {
        updateTreeProgress()
    }

    fun addPoints(pointsToAdd: Int) {
        _currentPoints.intValue += pointsToAdd
        // Bucle para manejar la posibilidad de subir varios niveles a la vez
        while (_currentPoints.intValue >= pointsForNextLevel) {
            if (_currentLevel.intValue < maxLevel) { // Limitar el nivel a 15
                _currentPoints.intValue -= pointsForNextLevel
                _currentLevel.intValue++
                updateTreeProgress()
            } else {
                // Si se alcanza el nivel máximo, los puntos se limitan al máximo
                _currentPoints.intValue = pointsForNextLevel
                break
            }
        }
    }

    private fun updateTreeProgress() {
        _treeProgress.floatValue = ((_currentLevel.intValue - 1).toFloat() / (maxLevel - 1).toFloat()).coerceIn(0.0f, 1.0f)
    }
}