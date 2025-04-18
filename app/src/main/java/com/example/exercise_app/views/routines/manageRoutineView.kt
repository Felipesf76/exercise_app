package com.example.exercise_app.views.routines

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.exercise_app.views.components.RoutineCard
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.exercise_app.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.navigation.NavHostController
import com.example.exercise_app.views.components.TitleComponent
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.colorResource
import com.example.exercise_app.data.utils.Screen

@Composable
fun ManageRoutineView(navController: NavHostController) {
    val routines = listOf("RUTINA 1", "RUTINA 2", "RUTINA 3", "RUTINA 4", "RUTINA 5","RUTINA 6", "RUTINA 7", "RUTINA 8", "RUTINA 9", "RUTINA 10")
    val scrollState = rememberScrollState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.ExerciseScreen.route)
                },
                containerColor = colorResource(R.color.Fourth),
                contentColor = colorResource(R.color.Primary)
            ){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.create_routine)
                )
            }
        }
    ){ contentPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .background(color = colorResource(R.color.Primary))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                TitleComponent(
                    title = stringResource(R.string.my_routines)
                )
                Spacer(modifier = Modifier.height(16.dp))
                routines.forEach { routine ->
                    RoutineCard(navController, routineName = routine)
                }
            }
        }
    }
}
