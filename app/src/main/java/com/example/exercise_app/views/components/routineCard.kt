package com.example.exercise_app.views.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.exercise_app.data.utils.Screen
import androidx.compose.ui.res.stringResource
import com.example.exercise_app.R

@Composable
fun RoutineCard(
    navController: NavHostController,
    routineName: String
) {
    Card(
        onClick = {
            navController.navigate(Screen.TrainingScreen.route)
        },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.third)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(
                elevation = 8.dp,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = routineName,
                    fontSize = 18.sp,
                    color = colorResource(R.color.Secondary)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Button(
                        onClick = {
                            Log.d("RoutineCard", "Editando $routineName")
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.Edit),
                            contentColor = colorResource(R.color.Primary)
                        )
                    ) {
                        Text(
                            stringResource(R.string.edit)
                        )
                    }

                    Button(
                        onClick = {
                            Log.d("RoutineCard", "Eliminando $routineName")
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.Delete),
                            contentColor = colorResource(R.color.Primary)
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.delete)
                        )
                    }
                }
            }

            IconButton(
                onClick = {
                    navController.navigate(Screen.TrainingScreen.route)
                }
            ) {
                Icon(
                    Icons.AutoMirrored.Outlined.ArrowForward,
                    contentDescription = stringResource(R.string.go_execise),
                    tint = colorResource(R.color.Secondary)
                )
            }
        }
    }
}
