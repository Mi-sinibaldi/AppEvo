package com.example.appevo.infra.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appevo.model.Departamento

interface DepartamentoDao {

    @Query("SELECT * FROM departamento")
    abstract fun getAll(): List<Departamento>

    @Insert
    abstract fun insert(departamento: Departamento)

    @Delete
    abstract fun delete(departamento: Departamento)

    @Update
    abstract fun update(departamento: Departamento)
}