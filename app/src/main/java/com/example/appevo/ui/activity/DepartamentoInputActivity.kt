package com.example.appevo.ui.activity

import android.app.Dialog
import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.example.appevo.R
import com.example.appevo.infra.AppDatabase
import com.example.appevo.infra.dao.DepartamentoDao
import com.example.appevo.model.Departamento
import kotlinx.android.synthetic.main.activity_departamento_input.*

class DepartamentoInputActivity : AppCompatActivity() {

    private lateinit var departamentoDao: DepartamentoDao
    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_departamento_input)

        val database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "evo-app-database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
        departamentoDao = database.departamentoDao()

        buttonCancelarDepartamento.setOnClickListener {
            finish()
        }

        configureSaveButton()
    }

    private fun configureSaveButton() {
        buttonSalvarDepartamento.setOnClickListener {
            saveDepto()
            showDialogConfirm()
        }
    }

    private fun saveDepto() {
        val createdDepartament = create()
        departamentoDao.insert(createdDepartament)
    }

    private fun create(): Departamento {
        val vnome = ediTextNomeDepartamento?.text.toString()
        val vsigla = ediTextSiglaDepartamento?.text.toString()
        return Departamento(nome = vnome, sigla = vsigla)
    }

    fun showDialogConfirm() {
        dialog = Dialog(this, R.style.CustomAlertDialog)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(R.layout.activity_check)
        val textView = dialog?.findViewById(R.id.textDialog) as TextView

        textView.setText("Departamento salvo com sucesso!")
        dialog?.setCancelable(false)
        dialog?.getWindow()!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        dialog?.show()

        val buttonDialogConfirm = dialog?.findViewById(R.id.buttonDialogConfirm) as Button
        buttonDialogConfirm.setOnClickListener({ v ->
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        })
    }
}
