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
import androidx.navigation.NavHostController
import com.example.exercise_app.model.Ejercicio


@Composable
fun TitleSelectExercise() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
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
fun SelectExerciseView(navController: NavHostController, ejercicios: List<Ejercicio>) {

    //Variables generadas
    var nombreRutina by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .background(color = Color.White),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            //titulo
            TitleSelectExercise()

            // TextBox
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = nombreRutina,
                onValueChange = { nombreRutina = it },
                label = { Text("Nombre de rutina:") },
                modifier = Modifier.fillMaxWidth()
            )

            //exercise list
            Spacer(modifier = Modifier.height(16.dp))
            ejercicios.forEach {
                ExerciseCard(
                    nombre = it.nombre,
                    descripcion = it.descripcion,
                    imagenResId = it.imagen?: R.drawable.fallback_image,
                    repeticiones = it.repeticiones,
                    series = it.series,
                    descanso = it.tiempoDescanso
                )
            }

            //submit buttom
            Button(
                onClick = {

                    Log.d("Rutina", "Creando rutina $nombreRutina")
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Crear rutina")
            }
        }
    }


}