package com.example.exercise_app.views.components

import android.util.Log
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exercise_app.R

@Composable
fun ExerciseCard(
    nombre: String,
    descripcion: String,
    imagenResId: Int,
    repeticiones: Int,
    series: Int,
    descanso: Int
) {
    var isChecked by remember { mutableStateOf(false) }

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
        Column(Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = nombre,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(R.color.Secondary)
                    ),
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = isChecked,
                    onCheckedChange = {
                        isChecked = it
                        Log.d("ExerciseCard", if (it) "$nombre agregado" else "$nombre eliminado")
                    },
                    colors = SwitchDefaults.colors (
                        checkedTrackColor = colorResource(R.color.Fourth),
                        checkedThumbColor = colorResource(R.color.Primary),
                        uncheckedTrackColor = colorResource(R.color.Primary),
                        uncheckedThumbColor = colorResource(R.color.Secondary)
                    )
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = imagenResId),
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
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
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
        }
    }
}