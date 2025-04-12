package com.example.exercise_app.views.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.exercise_app.views.components.RoutineCard
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.stringResource
import com.example.exercise_app.R

@Composable
fun TitleManageRoutine() {
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.title_home),
            style = TextStyle(
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Black
            )
        )
        Text(
            text = stringResource(R.string.welcome),
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Black
            )
        )
    }
}

@Composable
fun ManageRoutineView(padding: PaddingValues) {
    val routines = listOf("RUTINA 1", "RUTINA 2", "RUTINA 3", "RUTINA 4", "RUTINA 5")

    Column(modifier = Modifier.padding(padding).padding(16.dp)) {
        TitleHomePage()
        Spacer(modifier = Modifier.height(16.dp))
        routines.forEach { routine ->
            RoutineCard(routineName = routine)
        }
    }
}
