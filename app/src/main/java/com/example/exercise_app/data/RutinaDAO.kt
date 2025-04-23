package com.example.exercise_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.exercise_app.model.Rutina

@Dao
interface RutinaDAO {

    @Query("SELECT * FROM rutinas ORDER BY idRutinas DESC")
    suspend fun getRutinas(): List<Rutina>

    @Query("SELECT * FROM rutinas WHERE idRutinas = :id")
    suspend fun getRutinaPorId(id: Int): List<Rutina>

    @Upsert
    suspend fun upsertRutina(rutina : Rutina)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRutina(rutina: Rutina): Long

    @Delete
    suspend fun deleteRutina(rutina : Rutina)

    @Query("SELECT COUNT(*) FROM rutinas WHERE nombre = :nombreRutina")
    suspend fun existeRutinaPorNombre(nombreRutina: String): Int
}