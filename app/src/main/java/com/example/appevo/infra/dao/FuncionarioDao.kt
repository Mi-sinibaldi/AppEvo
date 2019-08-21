package com.example.appevo.infra.dao


import android.arch.persistence.room.*
import com.example.appevo.model.Funcionario

@Dao
interface FuncionarioDao {


    @Query("SELECT * FROM funcionario")
     fun getAll(): List<Funcionario>

    @Query("SELECT * FROM funcionario where IdFunc = :idFuncionario")
    fun getById(idFuncionario :  Int): Funcionario

    @Insert
     fun insert(funcionario: Funcionario)

    @Delete
     fun delete(funcionario: Funcionario)

    @Update
     fun update(funcionario: Funcionario)
}