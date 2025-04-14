package com.example.exercise_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.exercise_app.ui.theme.Exercise_appTheme
import com.example.exercise_app.views.exercises.SelectExerciseView
import com.example.exercise_app.views.home.BoxHomeScreen
import com.example.exercise_app.views.routines.ManageRoutineView
import com.example.exercise_app.views.training.TrainingView
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.exercise_app.data.utils.Screen
import androidx.compose.foundation.layout.padding

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Exercise_appTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = Screen.HomeScreen.route) {
                            BoxHomeScreen(navController)
                        }
                        //Otra página
                        composable(route = Screen.RoutineScreen.route) {
                            ManageRoutineView(navController)
                        }

                        composable(route = Screen.TrainingScreen.route) {
                            TrainingView(navController)
                        }

                    }
                }
            }
        }
    }
}