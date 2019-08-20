package com.example.appevo.ui.fragments


import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.appevo.R
import com.example.appevo.infra.AppDatabase
import com.example.appevo.infra.dao.FuncionarioDao
import com.example.appevo.model.Departamento
import com.example.appevo.ui.adapters.AdapterFunc
import com.example.appevo.model.Funcionario
import com.example.appevo.ui.activity.FuncionarioInputActivity
import kotlinx.android.synthetic.main.fragment_funcionario.view.*

class FuncionarioFragment : Fragment() {

    lateinit var adapterFunc: AdapterFunc
    private lateinit var funcionarioDao: FuncionarioDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View = inflater!!.inflate(R.layout.fragment_funcionario, container, false)

        val activity = activity as Context

//        val database = Room.databaseBuilder(
//            activity,
//            AppDatabase::class.java,
//            "evo-app-database")
//            .allowMainThreadQueries()
//            .build()
//
//        funcionarioDao = database.funcionarioDao()
        val recyclerViewDepto = view.findViewById<RecyclerView>(R.id.recyclerViewFunc)

        recyclerViewDepto.layoutManager = LinearLayoutManager(activity)
        recyclerViewDepto.adapter = AdapterFunc(listaFunc(), activity)

        view.buttonAddFunc.setOnClickListener { view ->
            val intent = Intent (getActivity(), FuncionarioInputActivity::class.java)
            getActivity()?.startActivity(intent)
        }
        return view
    }

    private fun listaFunc(): List<Funcionario> {
        val resut : MutableList<Funcionario>  = mutableListOf<Funcionario>()
        for (x in 0 until 30 step 1){
            //resut.add(x, Funcionario(x,"Funcionario  - $x",x,"", Departamento(x, "Deparatamento - $x","DPTO - $x")))
        }

        return resut

    }


}
