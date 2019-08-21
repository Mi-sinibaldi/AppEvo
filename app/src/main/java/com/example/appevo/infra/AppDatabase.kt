package com.example.appevo.infra


import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.appevo.infra.dao.DepartamentoDao
import com.example.appevo.infra.dao.FuncionarioDao
import com.example.appevo.model.Departamento
import com.example.appevo.model.Funcionario

@Database(entities = arrayOf(Departamento::class, Funcionario::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun departamentoDao(): DepartamentoDao
    abstract fun funcionarioDao(): FuncionarioDao
}