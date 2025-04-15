package com.example.exercise_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.example.exercise_app.model.RutinaEjercicio

@Dao
interface RutinaEjercicioDAO {

    @Query("SELECT * FROM rutinas_ejercicios")
    suspend fun getRutinasEjercicios(): List<RutinaEjercicio>

//    @Query("""
//        SELECT
//            r.nombre AS rutina,
//            e.nombre AS ejercicio
//        FROM rutinas_ejercicios re
//        JOIN rutinas r ON re.rutina_id = r.idRutinas
//        JOIN ejercicios e ON re.ejercicio_id = e.idEjercicios
//        WHERE r.nombre = :nombreRutina
//    """)
//    suspend fun getEjerciciosPorRutina(nombreRutina: String): List<RutinaEjercicio>
//
//    @Query("""
//        SELECT
//            r.idRutinas AS rutina_id,
//            r.nombre AS rutina,
//            e.idEjercicios AS ejercicio_id,
//            e.nombre AS ejercicio
//        FROM rutinas_ejercicios re
//        JOIN rutinas r ON re.rutina_id = r.idRutinas
//        JOIN ejercicios e ON re.ejercicio_id = e.idEjercicios
//        WHERE r.idRutinas = :idRutina
//    """)
//    suspend fun ge8(idRutina: Int): List<RutinaEjercicio>

    @Query("UPDATE rutinas_ejercicios SET ejercicio_id = :nuevoEjercicioId WHERE rutina_id = :rutinaId AND ejercicio_id = :ejercicioId")
    suspend fun actualizarEjercicioEnRutina(rutinaId: Int,ejercicioId: Int,nuevoEjercicioId: Int)

    @Delete
    suspend fun deleteRutinaEjercicio(rutinaEjercicio : RutinaEjercicio)

}