package com.example.exercise_app.views.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import com.example.exercise_app.data.Database
import com.example.exercise_app.data.utils.withArgs
import com.example.exercise_app.model.Rutina
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext


@Composable
fun RoutineCard(
    navController: NavHostController,
    routine: Rutina,
    db: Database
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

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
                    text = routine.nombre,
                    fontSize = 18.sp,
                    color = colorResource(R.color.Secondary)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Button(
                        onClick = {
                            routine.idRutinas?.let { id ->
                                navController.navigate(Screen.EditRoutineScreen.withArgs(id))
                            }
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
//                    Button(
//                        onClick = {
//                        //Log.d("RoutineCard", "Eliminando $routineName")
//                    },
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = colorResource(R.color.Delete),
//                            contentColor = colorResource(R.color.Primary)
//                        )
//                    )
                    Button(
                        onClick = {
                            scope.launch {
                                if (routine.idRutinas != null) {
                                    val relacionados = db.rutinaEjercicioDAO.getEjerciciosPorRutina(routine.idRutinas)
                                    if (relacionados.isEmpty()) {
                                        db.rutinaDao.deleteRutina(routine)
                                        //Toast.makeText(context, "Rutina eliminada correctamente", Toast.LENGTH_SHORT).show()
                                        //navController.popBackStack() // Vuelve a la pantalla anterior
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "No se puede eliminar la rutina. Aún tiene ejercicios relacionados.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.Delete),
                            contentColor = colorResource(R.color.Primary)
                        ),
                        //modifier = Modifier.align(Alignment.End)
                    )
                    {
                        Text(
                            stringResource(R.string.delete)
                        )
                    }
                }
            }

            IconButton(
                onClick = {
                    routine.idRutinas?.let { id ->
                        navController.navigate(Screen.TrainingScreen.withArgs(id))
                    }
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
