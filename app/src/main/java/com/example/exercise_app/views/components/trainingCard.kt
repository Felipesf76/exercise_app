package com.example.exercise_app.views.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.example.exercise_app.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.ui.platform.LocalContext

@Composable
fun TrainingCard(
    nombre: String,
    descripcion: String,
    imagenResId: String,
    repeticiones: Int,
    series: Int,
    descanso: Int,
    onEvaluar: (seriesIngresadas: Int, repeticionesIngresadas: Int, pesoIngresado: Float) -> Unit) {
    var numSeries by remember { mutableStateOf(0) }
    var numSeriesText by remember { mutableStateOf("") }

    var numRep by remember { mutableStateOf(0) }
    var numRepText by remember { mutableStateOf("") }

    var numPeso by remember { mutableStateOf(0f) }
    var numPesoText by remember { mutableStateOf("") }

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(
                elevation = 8.dp,
                shape = MaterialTheme.shapes.medium
            ),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.third)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            Modifier.padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = nombre,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(R.color.Secondary)
                    )
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            // Imagen del ejercicio y descripción
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                val context = LocalContext.current

                AsyncImage(
                    model = "android.resource://${context.packageName}/drawable/$imagenResId",
                    contentDescription = nombre,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 8.dp)
                )
                Column {
                    Text(
                        text = descripcion,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = colorResource(R.color.Secondary)
                        ),
                        maxLines = 4
                    )
                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "$descanso seg",
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = colorResource(R.color.Secondary)
                            )
                        )
                        Text(
                            text = "$repeticiones rep",
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = colorResource(R.color.Secondary)
                            )
                        )
                        Text(
                            text = "$series series",
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = colorResource(R.color.Secondary)
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Marcas de entrenamiento
            Text(
                text = stringResource(R.string.training_brands),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.Secondary)
                ),
            )

            // Campos para ingresar datos de entrenamiento
            Column {
                // Series input
                OutlinedTextField(
                    value = numSeriesText,
                    onValueChange = { value ->
                        if (value.matches(Regex("^\\d*\$"))) { // Solo dígitos
                            numSeriesText = value
                            value.toIntOrNull()?.let {
                                numSeries = it
                            }
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.series),
                            color = colorResource(R.color.Secondary)
                        )
                    },
                    placeholder = { Text("Ej: 3", color = colorResource(R.color.Secondary)) },
                    textStyle = TextStyle(color = colorResource(R.color.Secondary)),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Repeticiones input
                OutlinedTextField(
                    value = numRepText,
                    onValueChange = { value ->
                        if (value.matches(Regex("^\\d*\$"))) { // Solo dígitos
                            numRepText = value
                            value.toIntOrNull()?.let {
                                numRep = it
                            }
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.repetitions),
                            color = Color.Black
                        )
                    },
                    placeholder = { Text("Ej: 10", color = colorResource(R.color.Secondary)) },
                    textStyle = TextStyle(color = colorResource(R.color.Secondary)),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Peso input
                OutlinedTextField(
                    value = numPesoText,
                    onValueChange = { value ->
                        if (value.matches(Regex("^\\d*\\.?\\d{0,2}\$"))) { // Dígitos + punto opcional + hasta 2 decimales
                            numPesoText = value
                            value.toFloatOrNull()?.let {
                                numPeso = it
                            }
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.weight),
                            color = colorResource(R.color.Secondary)
                        )},
                    placeholder = { Text("Ej: 80.5", color = colorResource(R.color.Secondary)) },
                    textStyle = TextStyle(color = colorResource(R.color.Secondary)),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botón Evaluar
                Button(
                    onClick = {
                        onEvaluar(numSeries, numRep, numPeso)
                        Toast.makeText(context, "Evaluando ejercicio...", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.Success),
                        contentColor = colorResource(R.color.Primary)
                    )
                ) {
                    Text(text = stringResource(R.string.assess))
                }
            }
        }
    }
}

