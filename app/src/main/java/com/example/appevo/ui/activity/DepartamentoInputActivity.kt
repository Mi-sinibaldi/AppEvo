package com.example.appevo.ui.activity

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.appevo.R
import com.example.appevo.infra.AppDatabase
import com.example.appevo.infra.dao.DepartamentoDao
import com.example.appevo.model.Departamento
import kotlinx.android.synthetic.main.activity_departamento_input.*

class DepartamentoInputActivity : AppCompatActivity() {

    private lateinit var departamentoDao: DepartamentoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_departamento_input)

        val database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "evo-app-database")
            .allowMainThreadQueries()
            .build()
        departamentoDao = database.departamentoDao()

        configureSaveButton()
    }

    private fun configureSaveButton() {
        buttonAtualizarDepto.setOnClickListener {
            saveDepto()
            finish()
        }
    }

    private fun saveDepto() {
        val createdDepartament = create()
        departamentoDao.insert(createdDepartament)
    }

    private fun create(): Departamento {
        val vnome = editTextNomeDeptoUp?.text.toString()
        val vsigla = editTextSiglaDeptoUp?.text.toString()
        return Departamento(nome = vnome, sigla = vsigla)
    }
}
