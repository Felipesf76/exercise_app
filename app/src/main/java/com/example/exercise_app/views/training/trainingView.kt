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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exercise_app.R
import androidx.compose.material3.Text
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.navigation.NavHostController
import com.example.exercise_app.views.components.TrainingCard
import com.example.exercise_app.views.exercises.TitleSelectExercise

data class Exercise(
    val nombre: String,
    val descripcion: String,
    val imagenResId: Int,
    val series: Int,
    val repeticiones: Int,
    val descanso: Int
)

@Composable
fun TitleTrainingExercise() {
    Column(
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
fun TrainingView(navController: NavHostController) {
    val ejercicios = listOf(
        Exercise("Curl con barra", "Ejercicio principal para bíceps...", R.drawable.curl_barra, 4, 10, 60),
        Exercise("Fondos en paralelas", "Ejercicio compuesto, puede hacerse con peso...", R.drawable.fondos_paralelas, 4, 10, 60),
        Exercise("Press de banca", "Ejercicio base para pecho...", R.drawable.press_banca, 4, 8, 90),
        // Agrega más ejercicios según sea necesario
    )

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .background(color = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            //titulo
            TitleSelectExercise()


            //exercise list
            Spacer(modifier = Modifier.height(16.dp))
            ejercicios.forEach {
                TrainingCard(
                    nombre = it.nombre,
                    descripcion = it.descripcion,
                    imagenResId = it.imagenResId,
                    repeticiones = it.repeticiones,
                    series = it.series,
                    descanso = it.descanso
                )
            }

            //terminar entrenamiento buttom
            Button(
                onClick = {

                    Log.d("Entrenamiento Finalizado", "Entrenamiento Finalizado")
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = "Terminar Entrenamiento",
                    style = TextStyle(
                            color = Color.Black
                        )
                )
            }
        }
    }
}