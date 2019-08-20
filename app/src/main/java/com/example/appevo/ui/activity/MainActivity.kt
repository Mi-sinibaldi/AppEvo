package com.example.appevo.ui.activity

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import com.example.appevo.R
import com.example.appevo.ui.fragments.DeptoFragment
import com.example.appevo.ui.fragments.FuncionarioFragment
import kotlinx.android.synthetic.main.activity_chooser_sair.*


class MainActivity : AppCompatActivity() {

    private var dialog: Dialog? = null

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val fragment = DeptoFragment()
                //fragment.arguments = intent.extras
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout,fragment)
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_funcionario -> {

                val fragment = FuncionarioFragment()
                //fragment.arguments = intent.extras
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout,fragment)
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_close -> {
                showDialogChooser()
                return@OnNavigationItemSelectedListener true

            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val fragment = DeptoFragment()
        //fragment.arguments = intent.extras
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,fragment)
        transaction.commit()

//        //textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

    }

    fun showDialogChooser() {
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
