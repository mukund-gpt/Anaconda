package com.example.anaconda

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import com.example.anaconda.ui.theme.Custard
import com.example.anaconda.ui.theme.RoyalBlue


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
                .aspectRatio(2 / 3f)
        ) {
            val cellSize=size.width/20
            drawGameBoard(
                cellSize=cellSize,
                cellColor = Custard,
                borderCellColor = RoyalBlue,
                gridWidth = 20,
                gridHeight = 30

            )

        }
        Row (
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ){

            Button(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "REPLAY")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "START")
            }
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
    for(i in 0 until gridWidth){
        for (j in 0 until gridHeight){
            val isBorderCell=(i==0 || j==0 || i==gridWidth-1 || j==gridHeight-1)
            drawRect(
                color = if(isBorderCell)borderCellColor
                        else if((i+j)%2==0)cellColor
                        else cellColor.copy(alpha = 0.5f),
                topLeft = Offset(x=i*cellSize,y=j*cellSize),
                size= Size(cellSize,cellSize)
            )
        }
    }
}