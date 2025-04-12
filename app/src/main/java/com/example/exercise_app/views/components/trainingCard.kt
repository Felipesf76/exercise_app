package com.example.exercise_app.views.components

import android.util.Log
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TrainingCard(
    nombre: String,
    descripcion: String,
    imagenResId: Int,
    repeticiones: Int,
    series: Int,
    descanso: Int
) {
    var numSeries by remember { mutableStateOf(0) }
    var numRep by remember { mutableStateOf(0) }
    var numPeso by remember { mutableStateOf(0f) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(Modifier.padding(12.dp)) {
            // Título y descripción del ejercicio
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = nombre,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Imagen del ejercicio y descripción
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = imagenResId),
                    contentDescription = "Imagen del ejercicio",
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 8.dp)
                )
                Column {
                    Text(text = descripcion, fontSize = 14.sp, maxLines = 4)
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("$descanso seg", fontSize = 12.sp)
                        Text("$repeticiones rep", fontSize = 12.sp)
                        Text("$series series", fontSize = 12.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Marcas de entrenamiento
            Text("Marcas de entrenamiento", fontSize = 16.sp, fontWeight = FontWeight.Bold)

            // Campos para ingresar datos de entrenamiento
            Column {
                // Series input
                OutlinedTextField(
                    value = numSeries.toString(),
                    onValueChange = { value ->
                        numSeries = value.toIntOrNull() ?: 0
                    },
                    label = { Text("Series") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Repeticiones input
                OutlinedTextField(
                    value = numRep.toString(),
                    onValueChange = { value ->
                        numRep = value.toIntOrNull() ?: 0
                    },
                    label = { Text("Repeticiones") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Peso input
                OutlinedTextField(
                    value = numPeso.toString(),
                    onValueChange = { value ->
                        numPeso = value.toFloatOrNull() ?: 0f
                    },
                    label = { Text("Peso (Kg)") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botón Evaluar
                Button(
                    onClick = {
                        Log.d("ExerciseCard", "Series realizada: $numSeries\nRepeticiones: $numRep\nPeso: $numPeso kg")
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Evaluar")
                }
            }
        }
    }
}

