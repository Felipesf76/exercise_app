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
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.example.exercise_app.data.utils.Screen
import com.example.exercise_app.views.components.TitleComponent
import com.example.exercise_app.views.components.TrainingCard

data class Exercise(
    val nombre: String,
    val descripcion: String,
    val imagenResId: Int,
    val series: Int,
    val repeticiones: Int,
    val descanso: Int
)

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
            TitleComponent(
                //NOMBRE DE LA RUTINA
                title = "Nombre de la rutina"
            )

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