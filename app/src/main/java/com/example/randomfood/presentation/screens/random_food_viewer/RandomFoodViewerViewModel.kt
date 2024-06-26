package com.example.randomfood.presentation.screens.random_food_viewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomfood.domain.use_case.GetRandomFoodUseCase
import com.example.randomfood.domain.use_case.RegisterFavoriteFoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface RandomFoodViewerUiState {
    data object Initial : RandomFoodViewerUiState
    data class Success(val foodData: FoodViewerUiData) : RandomFoodViewerUiState
    data class Failure(val message: String) : RandomFoodViewerUiState
}

sealed interface RandomFoodViewerEvent {
    data object OnRendered : RandomFoodViewerEvent
    data object OnFavoriteButtonTapped : RandomFoodViewerEvent
}

@HiltViewModel
class RandomFoodViewerViewModel @Inject constructor(
    private val getRandomFoodUseCase: GetRandomFoodUseCase,
    private val registerFavoriteFoodUseCase: RegisterFavoriteFoodUseCase,
) : ViewModel() {
    private val _uiState : MutableStateFlow<RandomFoodViewerUiState> = MutableStateFlow(RandomFoodViewerUiState.Initial)
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: RandomFoodViewerEvent) {
        when (event) {
            RandomFoodViewerEvent.OnRendered -> {
                getRandomFood()
            }

            RandomFoodViewerEvent.OnFavoriteButtonTapped -> {
                registerFavoriteFood()
            }
        }
    }

    private fun getRandomFood() {
        viewModelScope.launch {
            val result = getRandomFoodUseCase()
            result.onSuccess { food ->
                _uiState.update {
                    RandomFoodViewerUiState.Success(
                        FoodViewerUiData.fromDomainFood(food)
                    )
                }
            }
            result.onFailure {  throwable ->
                _uiState.update {
                    RandomFoodViewerUiState.Failure(throwable.message ?: "Error!")
                }
            }
        }
    }

    private fun registerFavoriteFood() {
        viewModelScope.launch {
            val state = uiState.value as? RandomFoodViewerUiState.Success ?: return@launch
            registerFavoriteFoodUseCase(state.foodData.toFood())
        }
    }
}
