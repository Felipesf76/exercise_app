package com.example.exercise_app

import android.os.Bundle
import android.util.Log
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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.*
import androidx.room.Room
import com.example.exercise_app.data.Database
import com.example.exercise_app.model.Ejercicio
import com.example.exercise_app.views.components.snackbar.ObserveAsEvents
import com.example.exercise_app.views.components.snackbar.SnackbarController
import com.example.exercise_app.views.components.snackbar.SnackbarEvent
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dbFile = applicationContext.getDatabasePath(Database.DATABASE_NAME)
        val db = if (dbFile.exists()) {
            // Si ya existe, la usamos tal cual (no la sobreescribimos)
                 Room.databaseBuilder(
                     applicationContext,
                     Database::class.java,
                     Database.DATABASE_NAME
                 ).build()
            } else {
                // Si no existe, la copiamos desde assets
                Room.databaseBuilder(
                    applicationContext,
                    Database::class.java,
                    Database.DATABASE_NAME
                )
                    .createFromAsset("app_rutinas.db")
                    .build()
            }
        enableEdgeToEdge()

        setContent {
            Exercise_appTheme {
                val snackbarHostState = remember {
                    SnackbarHostState()
                }
                val scope = rememberCoroutineScope()
                ObserveAsEvents(
                    flow = SnackbarController.events
                ) { event ->
                    scope.launch {
                        snackbarHostState.currentSnackbarData?.dismiss()
                        val result = snackbarHostState.showSnackbar(
                            message = event.message,
                            actionLabel = event.action?.name,
                            duration = SnackbarDuration.Long
                        )

                        if (result == SnackbarResult.ActionPerformed) {
                            event.action?.action?.invoke()
                        }
                    }

                }
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = Screen.HomeScreen.route) {
                            BoxHomeScreen(navController)
                        }
                        composable(route = Screen.RoutineScreen.route) {
                            ManageRoutineView(navController)
                        }
                        composable(route = Screen.ExerciseScreen.route) {
                            SelectExerciseView(navController, db = db)

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
