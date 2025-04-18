package com.example.exercise_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.exercise_app.ui.theme.Exercise_appTheme
import com.example.exercise_app.views.exercises.SelectExerciseView
import com.example.exercise_app.views.home.BoxHomeScreen
import com.example.exercise_app.views.routines.ManageRoutineView
import com.example.exercise_app.views.training.TrainingView
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.exercise_app.data.utils.Screen
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.exercise_app.data.Database
import com.example.exercise_app.model.Ejercicio
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //DB connection
        val db = Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            Database.DATABASE_NAME
        )
            .createFromAsset("app_rutinas.db")
            .fallbackToDestructiveMigration()
            .build()

        lifecycleScope.launch {
            //RUTINAS
            val rutinas = db.rutinaDao.getRutinas()
            rutinas.forEach {
                Log.d("TEST_DB", "Rutina: ${it.idRutinas} - ${it.nombre}")
            }
        }

        enableEdgeToEdge()

        setContent {
            Exercise_appTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()
                    val ejercicios = remember { mutableStateOf<List<Ejercicio>>(emptyList()) }

                    Log.d("Imagen_aperturas_mancuernas", R.drawable.aperturas_mancuernas.toString())
                    Log.d("Imagen_buenos_dias", R.drawable.buenos_dias.toString())
                    Log.d("Imagen_curl_barra", R.drawable.curl_barra.toString())
                    Log.d("Imagen_curl_concentrado", R.drawable.curl_concentrado.toString())
                    Log.d("Imagen_curl_femoral", R.drawable.curl_femoral.toString())
                    Log.d("Imagen_curl_martillo", R.drawable.curl_martillo.toString())
                    Log.d("Imagen_dominadas", R.drawable.dominadas.toString())
                    Log.d("Imagen_elevaciones_laterales", R.drawable.elevaciones_laterales.toString())
                    Log.d("Imagen_encogimientos", R.drawable.encogimientos.toString())
                    Log.d("Imagen_extension_cuerda_polea", R.drawable.extension_cuerda_polea.toString())
                    Log.d("Imagen_extension_piernas", R.drawable.extension_piernas.toString())
                    Log.d("Imagen_farmers_walk", R.drawable.farmers_walk.toString())
                    Log.d("Imagen_fondos_paralelas", R.drawable.fondos_paralelas.toString())
                    Log.d("Imagen_hip_trust", R.drawable.hip_trust.toString())
                    Log.d("Imagen_jalon_pecho", R.drawable.jalon_pecho.toString())
                    Log.d("Imagen_pajaros_mancuernas", R.drawable.pajaros_mancuernas.toString())
                    Log.d("Imagen_patada_gluteo", R.drawable.patada_gluteo.toString())
                    Log.d("Imagen_peso_muerto", R.drawable.peso_muerto.toString())
                    Log.d("Imagen_pesso_rumano", R.drawable.pesso_rumano.toString())
                    Log.d("Imagen_prensa_pierna", R.drawable.prensa_pierna.toString())
                    Log.d("Imagen_press_banca", R.drawable.press_banca.toString())
                    Log.d("Imagen_press_frances", R.drawable.press_frances.toString())
                    Log.d("Imagen_press_inclinado", R.drawable.press_inclinado.toString())
                    Log.d("Imagen_press_militar", R.drawable.press_militar.toString())
                    Log.d("Imagen_remo_cuello", R.drawable.remo_cuello.toString())
                    Log.d("Imagen_remos_barra", R.drawable.remos_barra.toString())
                    Log.d("Imagen_sentadillas", R.drawable.sentadillas.toString())

                    LaunchedEffect(true) {
                        ejercicios.value = db.ejercicioDao.getEjercicios()
                    }

                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = Screen.HomeScreen.route) {
                            BoxHomeScreen(navController)
                        }
                        composable(route = Screen.RoutineScreen.route) {
                            ManageRoutineView(navController)
                        }
                        composable(route = Screen.ExerciseScreen.route) {
                            SelectExerciseView(navController, ejercicios.value)
                        }
                        composable(route = Screen.TrainingScreen.route) {
                            TrainingView(navController)
                        }
                    }
                }
            }
        }
    }
}
