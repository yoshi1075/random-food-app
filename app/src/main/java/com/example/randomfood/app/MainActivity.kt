package com.example.randomfood.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.randomfood.presentation.navigation.AppNavHost
import com.example.randomfood.ui.theme.RandomFoodTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomFoodTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}
