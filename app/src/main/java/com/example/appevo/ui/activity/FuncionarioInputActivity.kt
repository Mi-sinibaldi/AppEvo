package com.example.appevo.ui.activity

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.appevo.R
import com.example.appevo.infra.AppDatabase
import com.example.appevo.infra.dao.FuncionarioDao
import com.example.appevo.model.Departamento
import com.example.appevo.model.Funcionario
import kotlinx.android.synthetic.main.activity_departamento_input.*
import kotlinx.android.synthetic.main.activity_funcionario_input.*

class FuncionarioInputActivity : AppCompatActivity() {

//    private lateinit var funcionarioDao: FuncionarioDao
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_funcionario_input)
//
//        val database = Room.databaseBuilder(
//            this,
//            AppDatabase::class.java,
//            "evo-app-database"
//        )
//            .allowMainThreadQueries()
//            .build()
//        funcionarioDao = database.funcionarioDao()
//
//        configureSaveButton()
//    }
//
//    private fun configureSaveButton() {
//        buttonSalvar.setOnClickListener {
//            saveDepto()
//            finish()
//        }
//    }
//
//    private fun saveDepto() {
//        val createdFuncionario = create()
//        funcionarioDao.insert(createdFuncionario)
//    }
//
//    private fun create(): Funcionario {
//        val vnome = editTextNomeFunc?.text.toString()
//        val vrg = editTextRgFunc.setText(toString())
//        val vdepartamento = Departamento(id = 0, nome = "", sigla = "")
//
//        return Funcionario(nomeFunc = vnome, rg = vrg ,foto = "", departamento = vdepartamento  )
//
//    }
}


