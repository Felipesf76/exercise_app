package com.example.exercise_app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ejercicios")
data class Ejercicio (
    @PrimaryKey(autoGenerate = true) val idEjercicios: Int? = null,
    val nombre: String,
    val descripcion: String? = null,
    val imagen: Int? = null,
    val series: Int? = null,
    val repeticiones: Int? = null,
    val tiempoDescanso: Int? = null
)
