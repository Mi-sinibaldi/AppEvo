package com.example.appevo.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
class Funcionario(

    @PrimaryKey(autoGenerate = true)
    var idFunc: Int = 0,

    var nomeFunc: String,
    var rg: String
    //val departamento: Departamento
)