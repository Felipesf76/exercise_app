package com.example.exercise_app.data.utils

sealed class Screen (val route: String) {
    data object HomeScreen : Screen(route = "home_screen")
    data object TrainingScreen : Screen(route = "training_screen")
    data object RoutineScreen : Screen (route = "routine_screen")
    data object ExerciseScreen : Screen (route = "exercise_screen")
    data object EditRoutineScreen : Screen("edit_routine_screen")
}

fun Screen.withArgs(vararg args: Any): String {
    return buildString {
        append(this@withArgs.route)
        args.forEach { arg ->
            append("/$arg")
        }
    }
}