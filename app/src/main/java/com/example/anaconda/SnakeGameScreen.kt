package com.example.anaconda

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp

@Composable
fun SnakeGameScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text="Score: 0",
                style=MaterialTheme.typography.headlineMedium

            )
        }

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2/3f)
        ) {


        }
    }
    
}

private fun DrawScope.drawGameBoard(
    cellSize:Float,
    cellColor:Color,
    borderCellColor: Color,
    gridWidth:Int,
    gridHeight:Int

){

}