package com.example.exercise_app.views.components

import android.util.Log
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ExerciseCard(
    nombre: String?,
    descripcion: String?,
    imagenResId: Int,
    repeticiones: Int?,
    series: Int?,
    descanso: Int?
) {
    var isChecked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (nombre != null) {
                    Text(
                        text = nombre,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                }
                Switch(
                    checked = isChecked,
                    onCheckedChange = {
                        isChecked = it
                        Log.d("ExerciseCard", if (it) "$nombre agregado" else "$nombre eliminado")
                    }
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

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
                    if (descripcion != null) {
                        Text(text = descripcion, fontSize = 14.sp, maxLines = 4)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("$descanso seg", fontSize = 12.sp)
                        Text("$repeticiones rep", fontSize = 12.sp)
                        Text("$series series", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}