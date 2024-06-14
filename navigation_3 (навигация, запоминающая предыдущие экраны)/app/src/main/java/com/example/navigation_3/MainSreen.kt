package com.example.navigation_3

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold( // контейнер для навигации
        bottomBar = { // говорим, что наша навигация находиться внизу приложения
            MyBottomNavigation(navController = navController)
        }
    ) {
        NavGraph(navHostController = navController)
    }
}