package com.example.anaconda

import androidx.compose.ui.geometry.Offset

sealed class snakeGameEvent {
    data object StartGame:snakeGameEvent()
    data object PauseGame:snakeGameEvent()
    data object ResetGame:snakeGameEvent()
    data class UpdateDirection(val offset:Offset,val canvasWidth:Int):snakeGameEvent()
}