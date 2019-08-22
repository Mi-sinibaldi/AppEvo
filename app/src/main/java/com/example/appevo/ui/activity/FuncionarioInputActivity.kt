package com.example.appevo.ui.activity

import android.app.Dialog
import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*

import com.example.appevo.infra.AppDatabase
import com.example.appevo.infra.dao.DepartamentoDao
import com.example.appevo.infra.dao.FuncionarioDao
import com.example.appevo.model.Departamento
import com.example.appevo.model.Funcionario
import kotlinx.android.synthetic.main.activity_funcionario_input.*
import android.R



class FuncionarioInputActivity : AppCompatActivity() {

    private lateinit var funcionarioDao: FuncionarioDao
    private lateinit var deparamentoDao : DepartamentoDao
    private lateinit var departamentos : List<Departamento>
    private var departamentoSelecionadoId : Int = 0
    private lateinit var departamentoSelecionadoNome : String
    private var dialog: Dialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.appevo.R.layout.activity_funcionario_input)

        val database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "evo-app-database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

        deparamentoDao =  database.departamentoDao()

        // Initializing a String Array
        departamentos = deparamentoDao.getAll()

        val spinnerMap = arrayListOf<String>()

        departamentos.forEach {
            spinnerMap.add(it.nome)
        }

        // Initializing an ArrayAdapter
        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            spinnerMap.toList() // Array
        )

        // Set the drop down view resource
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Finally, data bind the spinner object with dapter
        spinnerFuncionario.adapter = adapter;

        // Set an on item selected listener for spinner object
        spinnerFuncionario.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                // Display the selected item text on text view
                //text_view.text = "Spinner selected : ${parent.getItemAtPosition(position).toString()}"

                val departamento = departamentos.get(position)
                departamentoSelecionadoId = departamento.id
                departamentoSelecionadoNome = departamento.nome
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }

        funcionarioDao = database.funcionarioDao()

        butonCancelarFuncionario.setOnClickListener {
            finish()
        }

        configureSaveButton()
    }

    private fun configureSaveButton() {
        buttonSalvarFuncionario.setOnClickListener {
            saveFuncionario()
            showDialogConfirm()
            //finish()
        }
    }

    private fun saveFuncionario() {
        val createdFuncionario = create()
        funcionarioDao.insert(createdFuncionario)
    }

    private fun create(): Funcionario {
        val vnome = editTextNomeFuncionario?.text.toString()
        val vrg = editTextRgFuncionario.text.toString()

        return Funcionario(nomeFunc = vnome, rg = vrg, departamentoId = departamentoSelecionadoId, deparamentoNome = departamentoSelecionadoNome)
    }

    fun showDialogConfirm() {
        dialog = Dialog(this, com.example.appevo.R.style.CustomAlertDialog)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(com.example.appevo.R.layout.activity_check)
        val textView = dialog?.findViewById(com.example.appevo.R.id.textDialog) as TextView

        textView.setText("Funcionário salvo com sucesso!")
        dialog?.setCancelable(false)
        dialog?.getWindow()!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        dialog?.show()

        val buttonDialogConfirm = dialog?.findViewById(com.example.appevo.R.id.buttonDialogConfirm) as Button
        buttonDialogConfirm.setOnClickListener({ v ->
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        })
    }

}
