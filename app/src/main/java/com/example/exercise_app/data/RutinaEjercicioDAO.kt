package com.example.exercise_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exercise_app.model.Ejercicio
import com.example.exercise_app.model.RutinaEjercicio

@Dao
interface RutinaEjercicioDAO {

    @Query("SELECT * FROM rutinas_ejercicios")
    suspend fun getRutinasEjercicios(): List<RutinaEjercicio>

    @Query("""
    SELECT e.* FROM ejercicios e
    INNER JOIN rutinas_ejercicios re ON e.idEjercicios = re.ejercicio_id
    WHERE re.rutina_id = :rutinaId
    """)
    suspend fun getEjerciciosPorRutina(rutinaId: Int): List<Ejercicio>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRutinaEjercicio(rutinaEjercicio: RutinaEjercicio): Long

    @Query("UPDATE rutinas_ejercicios SET ejercicio_id = :nuevoEjercicioId WHERE rutina_id = :rutinaId AND ejercicio_id = :ejercicioId")
    suspend fun actualizarEjercicioEnRutina(rutinaId: Int,ejercicioId: Int,nuevoEjercicioId: Int)

    //elimina todas las relaciones para la rutina cuyo ejercicio_id no este en la lista de entrada
    @Query("""
    DELETE FROM rutinas_ejercicios
    WHERE rutina_id = :rutinaId AND ejercicio_id NOT IN (:idsEjerciciosSeleccionados)
    """)
    suspend fun eliminarRelacionesNoSeleccionadas(rutinaId: Int, idsEjerciciosSeleccionados: List<Int>)


    @Query("DELETE FROM rutinas_ejercicios WHERE rutina_id = :rutinaId")
    suspend fun eliminarTodasLasRelacionesDeRutina(rutinaId: Int)

}