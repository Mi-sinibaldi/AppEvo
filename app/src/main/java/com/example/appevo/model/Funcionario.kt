package com.example.appevo.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Funcionario(

    @PrimaryKey(autoGenerate = true)
    val idFunc: Int = 0,

    val nomeFunc: String,
    val rg: Int
    //val departamento: Departamento
)