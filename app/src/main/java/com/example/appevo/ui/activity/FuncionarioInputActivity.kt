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
import android.widget.Toast.LENGTH_LONG
import com.example.appevo.R
import com.example.appevo.infra.AppDatabase
import com.example.appevo.infra.dao.FuncionarioDao
import com.example.appevo.model.Departamento
import com.example.appevo.model.Funcionario
import kotlinx.android.synthetic.main.activity_funcionario_input.*

class FuncionarioInputActivity : AppCompatActivity() {

    private lateinit var funcionarioDao: FuncionarioDao
    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funcionario_input)

        val database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "evo-app-database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
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


        return Funcionario(nomeFunc = vnome, rg = vrg)

    }

    fun showDialogConfirm() {
        dialog = Dialog(this, R.style.CustomAlertDialog)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(R.layout.activity_check)
        val textView = dialog?.findViewById(R.id.textDialog) as TextView

        textView.setText("Funcionário salvo com sucesso!")
        dialog?.setCancelable(false)
        dialog?.getWindow()!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        dialog?.show()

        val buttonDialogConfirm = dialog?.findViewById(R.id.buttonDialogConfirm) as Button
        buttonDialogConfirm.setOnClickListener({ v ->
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        })
    }

    fun spinnerDepto() {

        val personNames = arrayOf("Rahul", "Jack", "Rajeev", "Aryan", "Rashmi", "Jaspreet", "Akbar")
        val spinner = findViewById<Spinner>(R.id.spinnerFuncionario)
        if (spinner != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, personNames)
            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(
                        this@FuncionarioInputActivity,
                        getString(R.string.selected_item) + " " + personNames[position],
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }


    }
}
