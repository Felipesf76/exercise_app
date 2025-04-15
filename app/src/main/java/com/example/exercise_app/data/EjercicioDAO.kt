package com.example.exercise_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.exercise_app.model.Ejercicio
import kotlinx.coroutines.flow.Flow

@Dao
interface EjercicioDAO {

    @Query("SELECT * FROM ejercicios")
    suspend fun getEjercicios() : List<Ejercicio>

    @Query("SELECT * FROM ejercicios WHERE idEjercicios = :id")
    suspend fun getEjercicioPorId(id: Int): List<Ejercicio>

    @Upsert
    suspend fun upsertEjercicio(ejercicio: Ejercicio)

    @Delete
    suspend fun deleteEjercicio(ejercicio : Ejercicio)
}