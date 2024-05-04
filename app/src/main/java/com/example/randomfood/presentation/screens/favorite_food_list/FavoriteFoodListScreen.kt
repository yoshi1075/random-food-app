package com.example.randomfood.presentation.screens.favorite_food_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.compose.rememberImagePainter
import com.example.randomfood.presentation.navigation.Route

fun NavGraphBuilder.favoriteFoodListScreen(navigateToFavoriteFoodDetail: (foodId: Int) -> Unit) {
    composable(Route.FavoriteList.route) {
        val viewModel: FavoriteFoodListViewModel = hiltViewModel()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()
        FavoriteFoodListScreen(
            navigateToFavoriteFoodDetail,
            uiState,
            viewModel::onEvent,
        )
    }
}

@Composable
fun FavoriteFoodListScreen(
    navigateToFavoriteFoodDetail: (foodId: Int) -> Unit,
    uiState: State<FavoriteFoodListUiState>,
    onEvent: (FavoriteFoodListEvent) -> Unit,
) {
    LaunchedEffect(Unit) {
        onEvent(FavoriteFoodListEvent.OnRendered)
    }

    when {
        uiState.value.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Now loading...")
            }
        }

        uiState.value.errorMessage.isNotBlank() -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = uiState.value.errorMessage)
            }
        }

        uiState.value.favoriteFoodList.isNotEmpty() -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(uiState.value.favoriteFoodList) {food ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navigateToFavoriteFoodDetail(food.id)
                            }
                    ) {
                        Image(
                            rememberImagePainter(food.imageUrl),
                            "",
                            modifier = Modifier.size(100.dp),
                        )
                        Text(food.name)
                    }
                    Divider(thickness = 1.dp)
                }
            }
        }
    }
}