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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.example.exercise_app.data.Database
import com.example.exercise_app.data.RutinaDAO
import com.example.exercise_app.data.RutinaEjercicioDAO
import com.example.exercise_app.model.Ejercicio
import com.example.exercise_app.model.Rutina
import com.example.exercise_app.model.RutinaEjercicio
import kotlinx.coroutines.launch
import com.example.exercise_app.views.components.TitleComponent


@Composable
fun SelectExerciseView(navController: NavHostController, db: Database) {

    //variables desde Main
    val ejercicios = remember { mutableStateOf<List<Ejercicio>>(emptyList()) }

    LaunchedEffect(true) {
        ejercicios.value = db.ejercicioDao.getEjercicios()
    }

    //Variables generadas
    var nombreRutina by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val ejerciciosSeleccionados = remember { mutableStateListOf<Ejercicio>() }
    val context = LocalContext.current



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
            ejercicios.value.forEach { it ->
                val imagenNombre = it.imagenName
                val imageResId   = remember(imagenNombre) {
                    imagenNombre?.let {
                        context.resources.getIdentifier(it, "drawable", context.packageName)
                    } ?: R.drawable.fallback_image
                }

                ExerciseCard(
                    nombre = it.nombre,
                    descripcion = it.descripcion,
                    imagenResId = imageResId,
                    repeticiones = it.repeticiones,
                    series = it.series,
                    descanso = it.tiempoDescanso,
                    onCheckedChange = { isChecked ->
                        if (isChecked) {
                            ejerciciosSeleccionados.add(it)
                            Log.d("ExerciseCard", "${it.nombre} agregado")
                        } else {
                            ejerciciosSeleccionados.remove(it)
                            Log.d("ExerciseCard", "${it.idEjercicios} eliminado")
                        }
                    }
                )
            }

            //submit buttom
            Button(
                onClick = {

                    Log.d("Rutina", "Creando rutina $nombreRutina")

                    scope.launch {
                        val rutinaId = db.rutinaDao.insertRutina(Rutina(nombre = nombreRutina)).toInt()
                        Log.d("Rutina", "ID generado: $rutinaId")

                        val rutinaGuardada = db.rutinaDao.getRutinaPorId(rutinaId)

                        if (rutinaGuardada.isEmpty()) {
                            Log.e("Rutina", "Error: No se encontró la rutina con ID $rutinaId")
                            //********Error Message****
                            //********Success Message -> Return to Home****
                        } else {
                            Log.d("Rutina", "Rutina guardada correctamente: ${rutinaGuardada[0]}")

                            //agregando ejercicios a la rutina
                            ejerciciosSeleccionados.forEach{ it ->
                                val resultado = db.rutinaEjercicioDAO.insertRutinaEjercicio(
                                    RutinaEjercicio(rutina_id = rutinaId, ejercicio_id = it.idEjercicios!!)
                                )
                                if (resultado == -1L) {
                                    Log.e("Insert RutinaEjercicio", "Ya existe la relación rutina=${rutinaId} y ejercicio=${it.nombre}")
                                } else {
                                    Log.d("Insert RutinaEjercicio", "Insertado correctamente: rutina=${rutinaId}, ejercicio=${it.nombre}")
                                }
                            }
                        }

                        //close connecion
                        db.close()
                    }
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