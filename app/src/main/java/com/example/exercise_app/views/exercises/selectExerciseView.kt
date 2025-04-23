package com.example.exercise_app.views.exercises

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.exercise_app.R
import com.example.exercise_app.views.components.ExerciseCard
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavHostController
import com.example.exercise_app.data.Database
import com.example.exercise_app.data.utils.Screen
import com.example.exercise_app.model.Ejercicio
import com.example.exercise_app.model.Rutina
import com.example.exercise_app.model.RutinaEjercicio
import kotlinx.coroutines.launch
import com.example.exercise_app.views.components.TitleComponent
import com.example.exercise_app.views.components.snackbar.SnackbarController
import com.example.exercise_app.views.components.snackbar.SnackbarEvent


@Composable
fun SelectExerciseView(
    navController: NavHostController,
    db: Database,
    idRutina: Int? = null // null = crear, no null = editar

) {

    //variables
    val ejercicios = remember { mutableStateOf<List<Ejercicio>>(emptyList()) }
    val ejerciciosSeleccionados = remember { mutableStateListOf<Ejercicio>() }

    var nombreRutina by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val context = LocalContext.current


    LaunchedEffect(true) {


        //Check if idRutina es diferente a nulo
        if (idRutina != null) {
            val rutina = db.rutinaDao.getRutinaPorId(idRutina)
            nombreRutina = rutina.firstOrNull()?.nombre ?: ""

            val relacionados = db.rutinaEjercicioDAO.getEjerciciosPorRutina(idRutina)
            ejerciciosSeleccionados.clear()
            ejerciciosSeleccionados.addAll(relacionados)

            // Obtener todos los ejercicios
            val todos = db.ejercicioDao.getEjercicios()

            // Ordenar: primero los relacionados, luego el resto por ID
            val listaOrdenada = relacionados + todos.filter { ej ->
                relacionados.none { rel -> rel.idEjercicios == ej.idEjercicios }
            }.sortedBy { it.idEjercicios }

            ejercicios.value = listaOrdenada
        } else {
            // Obtener todos los ejercicios disponibles
            ejercicios.value = db.ejercicioDao.getEjercicios()
        }

    }


    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .background(color = colorResource(R.color.Primary)),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            //titulo
            TitleComponent(
                if (idRutina == null)
                    stringResource(R.string.create_routine)
                else
                    stringResource(R.string.edit_routine)
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
                textStyle = TextStyle(color = colorResource(R.color.Secondary)),
                modifier = Modifier.fillMaxWidth()
            )

            //exercise list
            Spacer(modifier = Modifier.height(16.dp))
            ejercicios.value.forEach { it ->
                val isSelected = ejerciciosSeleccionados.any { sel -> sel.idEjercicios == it.idEjercicios }
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
                    isRelated = isSelected,
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

                    Log.d("Rutina", "Intentando crear rutina con nombre: $nombreRutina")

                    scope.launch {
                        /** Logica de Editar**/

                        if (idRutina != null) {
                            //generamos lista con ids para elimina
                            val idsSeleccionados = ejerciciosSeleccionados.mapNotNull { it.idEjercicios }

                            //Elimina  itemes si no existe
                            if (idsSeleccionados.isNotEmpty()) {
                                db.rutinaEjercicioDAO.eliminarRelacionesNoSeleccionadas(idRutina, idsSeleccionados)
                            } else {
                                // Si no hay seleccionados, eliminamos TODAS las relaciones
                                db.rutinaEjercicioDAO.eliminarTodasLasRelacionesDeRutina(idRutina)
                            }

                            //actualiza el nombre de la rutina
                            val rutinaActualizada = Rutina(
                                idRutina,
                                nombre = nombreRutina
                            )

                            db.rutinaDao.upsertRutina(rutinaActualizada)
                            Log.d("Rutina", "Rutina actualizada: ${rutinaActualizada}")

                            //agregando ejercicios a la rutina
                            ejerciciosSeleccionados.forEach{ it ->
                                val resultado = db.rutinaEjercicioDAO.insertRutinaEjercicio(
                                    RutinaEjercicio(rutina_id = idRutina, ejercicio_id = it.idEjercicios!!)
                                )

                                //Evalua si hay error al evaluar un ejercicio
                                if (resultado == -1L) {
                                    scope.launch {
                                        SnackbarController.sendEvent(
                                            event = SnackbarEvent(
                                                message = "Error: Ya existe la relación rutina=${idRutina} y ejercicio=${it.nombre}"
                                            )
                                        )
                                        navController.navigate(Screen.RoutineScreen.route)
                                    }

                                } else {
                                    scope.launch {
                                        SnackbarController.sendEvent(
                                            event = SnackbarEvent(
                                                message = "Rutina editada exitosamente!"
                                            )
                                        )
                                        navController.navigate(Screen.RoutineScreen.route)
                                    }
                                }
                            }

                        }else{
                            /** Logica de Crear**/

                            //valida si el nombre de la rutina ya existe
                            val yaExiste = db.rutinaDao.existeRutinaPorNombre(nombreRutina)
                            Log.d("Rutina", "Resultado de existeRutinaPorNombre: $yaExiste")
                            if (yaExiste > 0) {
                                scope.launch {
                                    SnackbarController.sendEvent(
                                        event = SnackbarEvent(
                                            message = "Error: La rutina ya existe"
                                        )
                                    )
                                }

                            } else {
                                //Inserta un nuevo item
                                val rutinaId = db.rutinaDao.insertRutina(Rutina(nombre = nombreRutina)).toInt()
                                Log.d("Rutina", "ID generado: $rutinaId")

                                //valida que la rutina ya este disponible en db
                                val rutinaGuardada = db.rutinaDao.getRutinaPorId(rutinaId)

                                if (rutinaGuardada.isEmpty()) {
                                    scope.launch {
                                        SnackbarController.sendEvent(
                                            event = SnackbarEvent(
                                                message = "Error: No se encontró la rutina"
                                            )
                                        )
                                    }
                                } else {

                                    //agregando ejercicios a la rutina
                                    ejerciciosSeleccionados.forEach{ it ->
                                        val resultado = db.rutinaEjercicioDAO.insertRutinaEjercicio(
                                            RutinaEjercicio(rutina_id = rutinaId, ejercicio_id = it.idEjercicios!!)
                                        )

                                        //Evalua si hay error al evaluar un ejercicio
                                        if (resultado == -1L) {
                                            scope.launch {
                                                SnackbarController.sendEvent(
                                                    event = SnackbarEvent(
                                                        message = "Error: La rutina ya existe"
                                                    )
                                                )
                                            }
                                        } else {
                                            scope.launch {
                                                SnackbarController.sendEvent(
                                                    event = SnackbarEvent(
                                                        message = "Rutina creada"
                                                    )
                                                )
                                                navController.navigate(Screen.RoutineScreen.route)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.Success),
                    contentColor = colorResource(R.color.Primary)
                )
            ) {
                Text(
                    text = if (idRutina == null)
                        stringResource(R.string.create_routine)
                    else
                        stringResource(R.string.edit_routine)
                )
            }
        }
    }
}