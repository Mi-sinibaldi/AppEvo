package com.example.appevo.ui.activity

import android.app.Dialog
import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.example.appevo.R
import com.example.appevo.infra.AppDatabase
import com.example.appevo.infra.dao.DepartamentoDao
import com.example.appevo.model.Departamento
import kotlinx.android.synthetic.main.activity_departamento_update.*

class DepartamentoUpdateActivity : AppCompatActivity() {

    private lateinit var departamento: Departamento
    private lateinit var departamentoDao: DepartamentoDao
    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_departamento_update)

        val database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "evo-app-database"
        )
            .allowMainThreadQueries()
            .build()

        departamentoDao = database.departamentoDao()

        val id = intent.getIntExtra("Id", 0)

        departamento = departamentoDao.getById(id)

        PreencherObjetos()

        buttonDeletarDepartamento.setOnClickListener {
            DeleteDeparamento()
        }

        buttonAtualizarDepartamento.setOnClickListener {
            UpdateDepartamento()
        }
    }

    private fun UpdateDepartamento() {
        val departamento = create(departamento.id)
        departamentoDao.update(departamento)
        showDialogConfirmUpdate()
    }

    private fun DeleteDeparamento() {
        departamentoDao.delete(departamento)
        showDialogChooserDelete()
    }

    private fun PreencherObjetos() {
        editTextNomeDepartamentoUpdate.setText(departamento.nome)
        editTextSiglaDepartamentoUpdate.setText(departamento.sigla)
    }

    private fun create(id: Int): Departamento {
        val vnome = editTextNomeDepartamentoUpdate?.text.toString()
        val vsigla = editTextSiglaDepartamentoUpdate?.text.toString()
        return Departamento(id = id, nome = vnome, sigla = vsigla)
    }

    fun showDialogConfirmUpdate() {
        dialog = Dialog(this, R.style.CustomAlertDialog)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(R.layout.activity_check)
        val textView = dialog?.findViewById(R.id.textDialog) as TextView

        textView.setText("Departamento atualizado com sucesso!")
        dialog?.setCancelable(false)
        dialog?.getWindow()!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        dialog?.show()

        val buttonDialogConfirm = dialog?.findViewById(R.id.buttonDialogConfirm) as Button
        buttonDialogConfirm.setOnClickListener({ v ->
            startActivity(Intent(applicationContext, MainActivity::class.java))
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
