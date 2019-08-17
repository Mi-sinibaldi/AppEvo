package com.example.appevo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Funcionario(

    @PrimaryKey(autoGenerate = true)
    val idFunc: Int = 0,

    val nomeFunc: String,
    val rg: Int,
    val foto: String,
    val departamento: Departamento
)