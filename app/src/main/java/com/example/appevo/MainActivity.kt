package com.example.appevo

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.appevo.fragments.departamento.DeptoFragment
import com.example.appevo.fragments.departamento.UpdateDeptoFragment
import com.example.appevo.fragments.funcionario.FuncionarioFragment


class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val fragment = DeptoFragment()
                //fragment.arguments = intent.extras
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout,fragment)
                transaction.commit()
            }
            R.id.navigation_dashboard -> {

                val fragment = FuncionarioFragment()
                //fragment.arguments = intent.extras
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout,fragment)
                transaction.commit()
            }
            R.id.navigation_notifications -> {


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
}
