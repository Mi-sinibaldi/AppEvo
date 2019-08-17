package com.example.appevo.infra

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.appevo.infra.dao.DepartamentoDao
import com.example.appevo.infra.dao.FuncionarioDao

class DataBase {

    private lateinit var departamentoDao: DepartamentoDao
    private lateinit var funcionarioDao: FuncionarioDao

    private fun Database(c: Context) {


        // criando o banco de dados do aplicativo com o construtor de banco de dados do Room
        // MyBDKotlin é o nome do banco de dados.

        val appDatabase = Room.databaseBuilder(c, AppDatabase::class.java, "MyBDKotlin")
            .allowMainThreadQueries()
            .build()
        departamentoDao = appDatabase.departamentoDao()
        funcionarioDao = appDatabase.funcionarioDaoDao()

    }

}