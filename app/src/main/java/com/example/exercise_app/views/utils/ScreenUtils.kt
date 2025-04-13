package com.example.exercise_app.views.utils

sealed class Screen (val route: String) {
    data object HomeScreen : Screen("home_screen")
}