package com.example.anaconda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class snakeGameViewModel<Offset> :ViewModel() {
    private val _state= MutableStateFlow(snakeGameState())
    val state =_state.asStateFlow()


    fun onEvent(event: snakeGameEvent){
        when(event){
            snakeGameEvent.PauseGame -> {
                _state.update { it.copy(gameState=GameState.STARTED) }
                viewModelScope.launch {

                    while (state.value.gameState==GameState.STARTED){
                        val delayMillis=when(state.value.snake.size){
                            in 1..5->500L
                            in 6..10->450L
                            else->300L
                        }
                        delay(100)
                        _state.value=updateGame(state.value)
                    }
                }
            }
            snakeGameEvent.ResetGame ->{
                _state.update { it.copy(gameState=GameState.PAUSED) }
            }

            snakeGameEvent.StartGame -> {
                _state.value= snakeGameState()
            }
            is snakeGameEvent.UpdateDirection -> {
                updateDirection(event.offset,event.canvasWidth)
            }
        }

    }

    private fun updateDirection(offset: androidx.compose.ui.geometry.Offset, canvasWidth: Int) {

        if(!state.value.isGameOver){
            val cellSize=canvasWidth/state.value.xAxisGridSize
            val tapX=(offset.x/cellSize).toInt()
            val tapY=(offset.y/cellSize).toInt()
            val head=state.value.snake.first()

            _state.update {
                it.copy(
                    direction = when(state.value.direction){
                        Direction.UP ,Direction.DOWN-> {
                            if(tapX<head.x)Direction.LEFT else Direction.RIGHT
                        }

                        Direction.LEFT,Direction.RIGHT -> {
                            if(tapY<head.y)Direction.UP else Direction.DOWN
                        }

                    }
                )
            }
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
        if(
            currGame.snake.contains(newHead)||
            !isWithInBound(newHead,xAxisGridSize,yAxisGridSize)
            ){
            return currGame.copy(isGameOver =true)
        }
        //check if snake eats the food
        var newSnake= mutableListOf(newHead)+currGame.snake
        val newFood=if(newHead==currGame.food)snakeGameState.generateRandomFoodCoordinates()
        else currGame.food

        //update snake length
        if(newHead!=currGame.food){
            newSnake=newSnake.toMutableList()
            newSnake.removeAt(newSnake.size-1)
        }
        return currGame.copy(snake = newSnake, food = newFood)

    }
    private fun isWithInBound(
        coordinate: Coordinate,
        xAxisGidSize:Int,
        yAxisGridSize:Int
    ):Boolean{
    return coordinate.x in 1 until xAxisGidSize-1 &&
            coordinate.y in 1 until yAxisGridSize-1
    }
}