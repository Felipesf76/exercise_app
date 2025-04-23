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
import androidx.navigation.NavHostController
import com.example.exercise_app.views.components.TitleComponent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.exercise_app.data.Database
import com.example.exercise_app.model.Rutina
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ManageRoutineView(
    navController: NavHostController,
    db: Database
) {
    var routines by remember { mutableStateOf<List<Rutina>>(emptyList()) }

    fun loadRoutines() {
        CoroutineScope(Dispatchers.IO).launch {
            val loaded = db.rutinaDao.getRutinas()
            withContext(Dispatchers.Main) {
                routines = loaded
            }
        }
    }

    LaunchedEffect(Unit) {
        loadRoutines()
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .background(color = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TitleComponent(title = stringResource(R.string.my_routines))
            Spacer(modifier = Modifier.height(16.dp))
            routines.forEach { routine ->
                RoutineCard(navController, routine, db = db)
            }
        }
    }

}
