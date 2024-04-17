package com.example.anaconda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anaconda.ui.theme.AnacondaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnacondaTheme {
                val viewModel= viewModel<snakeGameViewModel<Any?>>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                
                SnakeGameScreen(
                    state=state,
                    onGameEvent = viewModel::onEvent

                )



            }
        }
    }
}


