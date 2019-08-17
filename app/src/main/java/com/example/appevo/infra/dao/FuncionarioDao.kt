package com.example.appevo.infra.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appevo.model.Funcionario

interface FuncionarioDao {


    @Query("SELECT * FROM funcionario")
    abstract fun getAll(): List<Funcionario>

    @Insert
    abstract fun insert(funcionario: Funcionario)

    @Delete
    abstract fun delete(funcionario: Funcionario)

    @Update
    abstract fun update(funcionario: Funcionario)
}