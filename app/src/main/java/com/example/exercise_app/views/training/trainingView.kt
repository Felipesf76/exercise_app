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
import kotlin.collections.firstOrNull

data class Exercise(
    val nombre: String,
    val descripcion: String,
    val imagenResId: String,
    val series: Int,
    val repeticiones: Int,
    val descanso: Int
)

data class ResultadoEjercicio(
    val nombre: String,
    val seriesEsperadas: Int,
    val repeticionesEsperadas: Int,
    val pesoIngresado: Float,
    val seriesIngresadas: Int,
    val repeticionesIngresadas: Int
)

@Composable
fun TrainingView(navController: NavHostController,db: Database, rutinaId: Int) {
    var exercises by remember { mutableStateOf<List<Ejercicio>>(emptyList()) }
    var rutinaNombre by remember { mutableStateOf<String>("") }
    val scrollState = rememberScrollState()
    var mostrarDialogo by remember { mutableStateOf(false) }
    var mensajeEvaluacion by remember { mutableStateOf("") }
    var resultados by remember { mutableStateOf(mutableListOf<ResultadoEjercicio>()) }


    fun loadExercisesbyRoutine() {
        CoroutineScope(Dispatchers.IO).launch {
            val loadedRutina = db.rutinaDao.getRutinaPorId(rutinaId).firstOrNull()
            val loaded = db.rutinaEjercicioDAO.getEjerciciosPorRutina(rutinaId)
            withContext(Dispatchers.Main) {
                rutinaNombre = loadedRutina?.nombre ?: "Rutina sin nombre"
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
                title = rutinaNombre
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
                    descanso = it.tiempoDescanso ?: 0,
                    onEvaluar = { seriesInput, repInput, pesoInput ->
                        resultados.removeAll { r -> r.nombre == it.nombre }
                        resultados.add(
                            ResultadoEjercicio(
                                nombre = it.nombre,
                                seriesEsperadas = it.series ?: 0,
                                repeticionesEsperadas = it.repeticiones ?: 0,
                                seriesIngresadas = seriesInput,
                                repeticionesIngresadas = repInput,
                                pesoIngresado = pesoInput
                            )
                        )
                    }
                )
            }

            //terminar entrenamiento button
            Button(
                onClick = {
                    mostrarDialogo = true
                    //navController.navigate(Screen.HomeScreen.route)
                },
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.Delete),
                    contentColor = colorResource(R.color.Primary)
                )
            ) {
                Text(text = stringResource(R.string.finish_training))
            }
        }
    }
    // Modal de evaluación
    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = { mostrarDialogo = false },
            confirmButton = {
                Button(onClick = {
                    mostrarDialogo = false
                    navController.navigate(Screen.HomeScreen.route)
                }) {
                    Text("Aceptar")
                }
            },
            title = { Text("Resumen del Entrenamiento") },
            text = {
                Column {
                    resultados.forEach {
                        val seriesOK = it.seriesIngresadas >= it.seriesEsperadas
                        val repOK = it.repeticionesIngresadas >= it.repeticionesEsperadas
                        val mensaje = buildString {
                            append("${it.nombre}: ")
                            if (seriesOK && repOK) append("¡Excelente, cumpliste o superaste el objetivo! 🎉")
                            else {
                                append("Puedes mejorar en ")
                                if (!seriesOK) append("series ")
                                if (!repOK) append("repeticiones")
                            }
                        }
                        Text(text = mensaje)
                    }
                }
            }
        )
    }
}