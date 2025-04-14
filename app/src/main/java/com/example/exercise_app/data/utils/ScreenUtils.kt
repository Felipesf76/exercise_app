package com.example.exercise_app.data.utils

sealed class Screen (val route: String) {
    data object HomeScreen : Screen(route = "home_screen")
    data object TrainingScreen : Screen(route = "training_screen")
    data object RoutineScreen : Screen (route = "routine_screen")
}