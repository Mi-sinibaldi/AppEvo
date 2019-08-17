package com.example.appevo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Departamento (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nome: String,
    val sigla: String
)