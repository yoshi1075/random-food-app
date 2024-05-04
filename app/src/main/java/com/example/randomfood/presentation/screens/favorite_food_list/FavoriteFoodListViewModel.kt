package com.example.randomfood.presentation.screens.favorite_food_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomfood.domain.model.Food
import com.example.randomfood.domain.use_case.GetFavoriteFoodListStreamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FavoriteFoodListUiState(
    val favoriteFoodList: List<Food> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = "",
)

sealed interface FavoriteFoodListEvent {
    data object OnRendered : FavoriteFoodListEvent
}

@HiltViewModel
class FavoriteFoodListViewModel @Inject constructor(
    private val getFavoriteFoodListStreamUseCase: GetFavoriteFoodListStreamUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoriteFoodListUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: FavoriteFoodListEvent) {
        when (event) {
            FavoriteFoodListEvent.OnRendered -> {
                getFavoriteFoodListStream()
            }
        }
    }

    private fun getFavoriteFoodListStream() {
        try {
            viewModelScope.launch {
                _uiState.update {
                    FavoriteFoodListUiState(isLoading = true)
                }
                getFavoriteFoodListStreamUseCase()
                    .onEach { foodList ->
                        _uiState.update {
                            it.copy(
                                favoriteFoodList = foodList,
                                isLoading = false
                            )
                        }
                    }
                    .stateIn(viewModelScope)
            }
        } catch (e: Exception) {
            _uiState.update {
                it.copy(errorMessage = e.message ?: "Error!")
            }
        }
    }
}
