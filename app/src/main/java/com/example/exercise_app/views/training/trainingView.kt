package com.example.exercise_app.views.training

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.exercise_app.R
import androidx.compose.material3.Text
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.example.exercise_app.data.Database
import com.example.exercise_app.model.Ejercicio
import com.example.exercise_app.data.utils.Screen
import com.example.exercise_app.views.components.TitleComponent
import com.example.exercise_app.views.components.TrainingCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class Exercise(
    val nombre: String,
    val descripcion: String,
    val imagenResId: String,
    val series: Int,
    val repeticiones: Int,
    val descanso: Int
)

@Composable
fun TrainingView(navController: NavHostController,db: Database, rutinaId: Int) {
    var exercises by remember { mutableStateOf<List<Ejercicio>>(emptyList()) }
    val scrollState = rememberScrollState()

    fun loadExercisesbyRoutine() {
        CoroutineScope(Dispatchers.IO).launch {
            val loaded = db.rutinaEjercicioDAO.getEjerciciosPorRutina(rutinaId)
            withContext(Dispatchers.Main) {
                exercises = loaded
            }
        }
    }

    LaunchedEffect(Unit) {
        loadExercisesbyRoutine()
    }

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .background(color = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TitleComponent(
                //NOMBRE DE LA RUTINA
                title = "Nombre de la rutina"
            )

            //exercise list
            Spacer(modifier = Modifier.height(16.dp))
            exercises.forEach {
                TrainingCard(
                    nombre = it.nombre,
                    descripcion = it.descripcion.toString(),
                    imagenResId = it.imagenName.toString(),
                    series = it.series ?: 0,
                    repeticiones = it.repeticiones ?: 0,
                    descanso = it.tiempoDescanso ?: 0
                )
            }

            //terminar entrenamiento button
            Button(
                onClick = {
                    navController.navigate(Screen.HomeScreen.route)
                },
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.Delete),
                    contentColor = colorResource(R.color.Primary)
                )
            ) {
                Text(
                    text = stringResource(R.string.finish_training)
                )
            }
        }
    }
}