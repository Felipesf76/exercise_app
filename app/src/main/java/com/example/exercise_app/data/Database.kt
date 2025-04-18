package com.example.exercise_app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.exercise_app.model.Ejercicio
import com.example.exercise_app.model.Rutina
import com.example.exercise_app.model.RutinaEjercicio

@Database(entities = [Rutina::class, Ejercicio::class, RutinaEjercicio::class], version = 2)
abstract class Database : RoomDatabase(){

    abstract val rutinaDao: RutinaDAO
    abstract val ejercicioDao: EjercicioDAO
    abstract val rutinaEjercicioDAO: RutinaEjercicioDAO

    companion object{
        const val DATABASE_NAME = "app_rutinas.db"
    }

}
