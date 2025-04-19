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
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.exercise_app.data.Database
import com.example.exercise_app.model.Rutina
import com.example.exercise_app.data.utils.Screen
import com.example.exercise_app.views.home.TitleHomePage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
fun ManageRoutineView(
    navController: NavHostController,
    db: Database
) {
    val context = LocalContext.current
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
//        val loadedRoutines = withContext(Dispatchers.IO) {
//            db.rutinaDao.getRutinas()
//        }
        loadRoutines()
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .background(color = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TitleHomePage()
            Spacer(modifier = Modifier.height(16.dp))
            routines.forEach { routine ->
                RoutineCard(navController, routine, db = db)
            }
        }
    }

}
