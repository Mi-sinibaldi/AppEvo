package com.example.appevo.infra

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appevo.infra.dao.DepartamentoDao
import com.example.appevo.infra.dao.FuncionarioDao
import com.example.appevo.model.Departamento
import com.example.appevo.model.Funcionario

@Database(entities = [Departamento::class, Funcionario::class ], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun departamentoDao(): DepartamentoDao
    abstract fun funcionarioDaoDao(): FuncionarioDao
}