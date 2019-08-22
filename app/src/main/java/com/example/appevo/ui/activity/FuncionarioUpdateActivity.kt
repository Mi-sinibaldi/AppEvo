package com.example.appevo.ui.activity

import android.app.Dialog
import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.example.appevo.R
import com.example.appevo.infra.AppDatabase
import com.example.appevo.infra.dao.DepartamentoDao
import com.example.appevo.infra.dao.FuncionarioDao
import com.example.appevo.model.Departamento
import com.example.appevo.model.Funcionario
import kotlinx.android.synthetic.main.activity_funcionario_update.*

class FuncionarioUpdateActivity : AppCompatActivity() {

    private lateinit var funcionario: Funcionario
    private lateinit var funcionarioDao: FuncionarioDao
    private lateinit var deparamentoDao: DepartamentoDao
    private lateinit var departamentos: List<Departamento>
    private var departamentoSelecionadoId: Int = 0
    private lateinit var departamentoSelecionadoNome: String
    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funcionario_update)

        val database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "evo-app-database"
        )
            .allowMainThreadQueries()
            .build()

        deparamentoDao = database.departamentoDao()

        // Initializing a String Array
        departamentos = deparamentoDao.getAll()

        val spinnerMap = arrayListOf<String>()

        departamentos.forEach {
            spinnerMap.add(it.nome)
        }

        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            spinnerMap.toList() // Array
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFuncionarioUpdate.adapter = adapter;

        spinnerFuncionarioUpdate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                val departamento = departamentos.get(position)
                departamentoSelecionadoId = departamento.id
                departamentoSelecionadoNome = departamento.nome
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        funcionarioDao = database.funcionarioDao()
        val id = intent.getIntExtra("Id", 0)

        funcionario = funcionarioDao.getById(id)

        buttonDeletarFuncionario.setOnClickListener {
            DeleteFuncionario()
        }

        buttonUpdateFuncionario.setOnClickListener {
            UpdateFuncionario()
        }

        PreencherObjetos()
    }

    private fun UpdateFuncionario() {
        var funcionario = create(funcionario.idFunc)
        funcionarioDao.update(funcionario)
        showDialogConfirmUpdate()
    }

    private fun DeleteFuncionario() {
        funcionarioDao.delete(funcionario)
        showDialogChooserDelete()
    }

    private fun PreencherObjetos() {
        TexViewNomeFuncionarioUpdate.setText(funcionario.nomeFunc)
        editTetxtRgFuncionarioUpdate.setText(funcionario.rg)

        departamentos.forEachIndexed { index, departamento ->
            if (departamento.id == funcionario.departamentoId) {
                spinnerFuncionarioUpdate.setSelection(index)
            }
        }
    }

    private fun create(id: Int): Funcionario {
        val vnome = TexViewNomeFuncionarioUpdate?.text.toString()
        val vrg = editTetxtRgFuncionarioUpdate?.text.toString()
        return Funcionario(
            idFunc = id,
            nomeFunc = vnome,
            rg = vrg,
            departamentoId = departamentoSelecionadoId,
            deparamentoNome = departamentoSelecionadoNome
        )
    }

    fun showDialogConfirmUpdate() {
        dialog = Dialog(this, R.style.CustomAlertDialog)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(R.layout.activity_check)
        val textView = dialog?.findViewById(R.id.textDialog) as TextView

        textView.setText("Funcionário atualizado com sucesso!")
        dialog?.setCancelable(false)
        dialog?.getWindow()!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        dialog?.show()

        val buttonDialogConfirm = dialog?.findViewById(R.id.buttonDialogConfirm) as Button
        buttonDialogConfirm.setOnClickListener({ v ->
            startActivity(Intent(applicationContext, MainActivity::class.java))
            dialog?.dismiss()
            finish()
        })
    }

    fun showDialogChooserDelete() {
        dialog = Dialog(this, R.style.CustomAlertDialog)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(R.layout.activity_chooser_sair)
        dialog?.setCancelable(false)
        dialog?.getWindow()!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        dialog?.show()

        val button_dialog_chooser_yes = dialog?.findViewById(R.id.button_dialog_chooser_yes) as Button
        val buttton_dialog_chooser_no = dialog?.findViewById(R.id.buttton_dialog_chooser_no) as Button

        button_dialog_chooser_yes.setOnClickListener {
            finish()
        }
        buttton_dialog_chooser_no.setOnClickListener {
            dialog?.dismiss()
        }
    }
}
