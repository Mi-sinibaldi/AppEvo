package com.example.appevo.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Departamento (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nome: String,
    val sigla: String
)