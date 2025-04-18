package com.example.exercise_app.views.exercises

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
import com.example.exercise_app.views.components.ExerciseCard
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.example.exercise_app.views.components.TitleComponent

data class Ejercicio(
    val nombre: String,
    val descripcion: String,
    val imagenResId: Int,
    val series: Int,
    val repeticiones: Int,
    val descanso: Int
)

@Composable
fun SelectExerciseView(navController: NavHostController) {
    val ejercicios = listOf(
        Ejercicio("Curl con barra", "Ejercicio principal para bíceps...", R.drawable.curl_barra, 4, 10, 60),
        Ejercicio("Fondos en paralelas", "Ejercicio compuesto, puede hacerse con peso...", R.drawable.fondos_paralelas, 4, 10, 60),
        Ejercicio("Press de banca", "Ejercicio base para pecho...", R.drawable.press_banca, 4, 8, 90),
        // Agrega más ejercicios según sea necesario
    )

    //Variables generadas
    var nombreRutina by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .background(color = colorResource(R.color.Primary)),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            //titulo
            TitleComponent(
                //Modificar si es creación o editar
                stringResource(R.string.create_routine)
            )

            // TextBox
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = nombreRutina,
                onValueChange = { nombreRutina = it },
                label = {
                    Text(
                        text = stringResource(R.string.routin_name),
                        color = colorResource(R.color.Secondary)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            //exercise list
            Spacer(modifier = Modifier.height(16.dp))
            ejercicios.forEach {
                ExerciseCard(
                    nombre = it.nombre,
                    descripcion = it.descripcion,
                    imagenResId = it.imagenResId,
                    repeticiones = it.repeticiones,
                    series = it.series,
                    descanso = it.descanso
                )
            }

            //submit buttom
            Button(
                onClick = {
                    Log.d("Rutina", "Creando rutina $nombreRutina")
                },
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.Success),
                    contentColor = colorResource(R.color.Primary)
                )
            ) {
                Text(
                    // HACER LA LÓGICA PARA CREAR O EDITAR RUTINA
                    text = stringResource(R.string.create_routine)
                )
            }
        }
    }
}