package com.example.appevo.infra.dao

import android.arch.persistence.room.*
import com.example.appevo.model.Departamento

@Dao
interface DepartamentoDao {

    @Query("SELECT * FROM departamento")
    fun getAll(): List<Departamento>

    @Query("SELECT * FROM departamento where Id = :idDepartamento")
    fun getById(idDepartamento :  Int): Departamento

    @Insert
     fun insert(departamento: Departamento)

    @Delete
     fun delete(departamento: Departamento)

    @Update
     fun update(departamento: Departamento)
}