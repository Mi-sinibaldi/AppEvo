package com.example.appevo.infra


import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.appevo.infra.dao.DepartamentoDao
import com.example.appevo.model.Departamento

@Database(entities = arrayOf(Departamento::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun departamentoDao(): DepartamentoDao
}