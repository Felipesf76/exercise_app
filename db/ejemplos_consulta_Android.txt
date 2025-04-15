package com.example.exercise_app

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.exercise_app.data.Database
import com.example.exercise_app.model.Ejercicio
import com.example.exercise_app.model.Rutina
import com.example.exercise_app.ui.theme.Exercise_appTheme
import com.example.exercise_app.views.home.BoxHomeScreen
import kotlinx.coroutines.launch
import java.io.FileOutputStream


class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            val db = Room.databaseBuilder(
                applicationContext,
                Database::class.java,
                Database.DATABASE_NAME
            )
                .createFromAsset("app_rutinas.db")
                .build()

            lifecycleScope.launch {

                //RUTINAS
                val rutinas = db.rutinaDao.getRutinas()
                rutinas.forEach {
                    Log.d("TEST_DB", "Rutina: ${it.idRutinas} - ${it.nombre}")
                }

            }

                lifecycleScope.launch {
                    val rutina = Rutina(
                        idRutinas = 6,
                        nombre = "RUTINA SABADO",
                    )

                    db.rutinaDao.deleteRutina(rutina)
                }

                lifecycleScope.launch {

                    //RUTINAS
                    val rutinas = db.rutinaDao.getRutinas()
                    rutinas.forEach {
                        Log.d("TEST_DB", "Rutina: ${it.idRutinas} - ${it.nombre}")
                    }

//                //EJERCICIOS
//                val ejercicios = db.ejercicioDao.getEjercicios()
//                ejercicios.forEach {
//                    Log.d("TEST_DB", "Ejercicios: ${it.idEjercicios} - ${it.nombre} - ${it.descripcion} - ${it.imagen} - ${it.series} - ${it.repeticiones} - ${it.tiempoDescanso}")
//                }
//
//                //EJERCICIOS POR ID
//                lifecycleScope.launch {
//                    val ejercicios = db.ejercicioDao.getEjercicioPorId(1) // Cambia el ID según lo que necesites
//                    ejercicios.forEach {
//                        Log.d("TEST_DB", "Ejercicio: ${it.idEjercicios} - ${it.nombre}")
//                    }
//                }

//                //actualizar o insertar
//                lifecycleScope.launch {
//                    val ejercicio = Ejercicio(
//                        idEjercicios = 54,  // Cambia el ID y los demás parámetros según lo necesites
//                        nombre = "Extensión de piernas actualizado",
//                        descripcion = "Nueva descripción",
//                        imagen = "imagen_url",
//                        series = 3,
//                        repeticiones = 15,
//                        tiempoDescanso = 60
//                    )
//
//                    db.ejercicioDao.upsertEjercicio(ejercicio)
//                    val actualizado = db.ejercicioDao.getEjercicioPorId(54)
//                    Log.d("TEST_DB", "Después del upsert: ${actualizado.firstOrNull()}")
//                }


                //codigo comentado para probar los datos de la base de datos cuano el asset cargue una base de datos erronea
//                try {
//                    val rutinas = db.rutinaDao.getRutinas()
//
//                    if (rutinas.isEmpty()) {
//                        Log.d("TEST_DB", "No se encontraron rutinas.")
//                    } else {
//                        for (rutina in rutinas) {
//                            Log.d("TEST_DB", "Rutina: ${rutina.idRutinas} - ${rutina.nombre}")
//                        }
//                    }
//                } catch (e: Exception) {
//                    Log.e("TEST_DB", "Error accediendo a la base de datos", e)
//                }
            }

        enableEdgeToEdge()
        setContent {
            Exercise_appTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BoxHomeScreen(innerPadding)
                }
            }
        }
    }
}