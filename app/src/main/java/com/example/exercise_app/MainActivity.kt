package com.example.exercise_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.exercise_app.ui.theme.Exercise_appTheme
import com.example.exercise_app.views.home.ManageRoutineView
import com.example.exercise_app.views.home.SelectExerciseView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Exercise_appTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //ManageRoutineView(innerPadding)
                    SelectExerciseView(innerPadding)
                }
            }
        }
    }
}