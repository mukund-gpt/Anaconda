package com.example.anaconda

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class snakeGameViewModel :ViewModel() {
    private val _state= MutableStateFlow(snakeGameState())
    val state =_state.asStateFlow()


    fun onEvent(event: snakeGameEvent){
        when(event){
            snakeGameEvent.PauseGame -> TODO()
            snakeGameEvent.ResetGame ->{
                _state.update { it.copy(gameState=GameState.PAUSED) }
            }

            snakeGameEvent.StartGame -> {
                _state.value= snakeGameState()
            }
            is snakeGameEvent.UpdateDirection -> TODO()
        }

    }
    private fun updateGame(currGame:snakeGameState):snakeGameState{
        if(currGame.isGameOver){
            return currGame
        }
        val head=currGame.snake.first()
        val xAxisGridSize=currGame.xAxisGridSize
        val yAxisGridSize=currGame.yAxisGridSize

        //update moment of snake
        val newHead=when(currGame.direction){
            Direction.UP ->{
                Coordinate(x=head.x,y=head.y-1)
            }
            Direction.DOWN -> {
                Coordinate(x=head.x,y=head.y+1)
            }
            Direction.LEFT -> {
                Coordinate(x=head.x-1,y=head.y)
            }
            Direction.RIGHT -> {
                Coordinate(x=head.x+1,y=head.y)
            }

        }
        //check if snake collide or goes out of bounds

    }
}