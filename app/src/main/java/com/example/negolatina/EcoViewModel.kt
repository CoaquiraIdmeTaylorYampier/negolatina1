package com.example.negolatina

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class EcoViewModel : ViewModel() {
    val currentPoints = mutableStateOf(0)
    val currentLevel = mutableStateOf(1)
    val treeProgress = mutableStateOf(0f)
    val pointsForNextLevel = 100

    fun addPoints(points: Int) {
        currentPoints.value += points
        if (currentPoints.value >= pointsForNextLevel) {
            levelUp()
        }
        updateProgress()
    }

    private fun levelUp() {
        currentPoints.value -= pointsForNextLevel
        currentLevel.value++
    }

    private fun updateProgress() {
        treeProgress.value = currentPoints.value.toFloat() / pointsForNextLevel
    }
}
