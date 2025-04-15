package com.example.exercise_app.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "rutinas_ejercicios",
    foreignKeys = [
        ForeignKey(
            entity = Rutina::class,
            parentColumns = ["idRutinas"],
            childColumns = ["rutina_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Ejercicio::class,
            parentColumns = ["idEjercicios"],
            childColumns = ["ejercicio_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["rutina_id", "ejercicio_id"]
)
data class RutinaEjercicio (
    val rutina_id: Int,
    val ejercicio_id: Int
)