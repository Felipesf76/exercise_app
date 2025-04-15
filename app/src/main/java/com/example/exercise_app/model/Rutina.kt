package com.example.exercise_app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rutinas")
data class Rutina (
    @PrimaryKey(autoGenerate = true) val idRutinas: Int? = null,
    val nombre: String = ""
)

