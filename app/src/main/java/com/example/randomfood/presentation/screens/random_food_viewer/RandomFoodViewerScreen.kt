package com.example.randomfood.presentation.screens.random_food_viewer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.randomfood.presentation.navigation.Route

fun NavGraphBuilder.randomFoodViewerScreen(navigateToRandomFoodViewer: () -> Unit) {
    composable(Route.RandomFoodViewer.route) {
        val viewModel: RandomFoodViewerViewModel = hiltViewModel()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()
        RandomFoodViewerScreen(
            uiState = uiState,
            onEvent = viewModel::onEvent,
            navigateToRandomFoodViewer
        )
    }
}

@Composable
fun RandomFoodViewerScreen(
    uiState: State<RandomFoodViewerUiState>,
    onEvent: (RandomFoodViewerEvent) -> Unit,
    navigateToRandomFoodViewer: () -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(RandomFoodViewerEvent.OnRendered)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        when (val state = uiState.value) {
            is RandomFoodViewerUiState.Success -> {
                Button(onClick = { navigateToRandomFoodViewer() }) {
                    Text(text = state.url)
                }
            }
            is RandomFoodViewerUiState.Failure -> {
                Text(text = state.message)
            }
        }
    }
}
