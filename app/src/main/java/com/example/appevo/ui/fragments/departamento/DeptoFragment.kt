package com.example.appevo.ui.fragments.departamento

import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appevo.R
import com.example.appevo.infra.AppDatabase
import com.example.appevo.infra.dao.DepartamentoDao
import com.example.appevo.ui.adapters.AdapterDepto
import com.example.appevo.model.Departamento
import com.example.appevo.ui.activity.DepartamentoImputActivity
import kotlinx.android.synthetic.main.fragment_depto.view.*

class DeptoFragment : Fragment() {


    lateinit var adapterDepto: AdapterDepto
    private lateinit var departamentoDao: DepartamentoDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view: View = inflater!!.inflate(R.layout.fragment_depto, container, false)

        val activity = activity as Context

        val database = Room.databaseBuilder(
            activity,
            AppDatabase::class.java,
            "evo-app-database")
            .allowMainThreadQueries()
            .build()

        departamentoDao = database.departamentoDao()
        val recyclerViewDepto = view.findViewById<RecyclerView>(R.id.recyclerViewFunc)

        recyclerViewDepto.layoutManager = LinearLayoutManager(activity)
        recyclerViewDepto.adapter = AdapterDepto(listaDeDeptos(), activity)

        view.buttonAddDepto.setOnClickListener {
            val intent = Intent (getActivity(), DepartamentoImputActivity::class.java)
            getActivity()?.startActivity(intent)
        }
        return view
    }

    private fun listaDeDeptos(): List<Departamento> {
        return  departamentoDao.getAll()
    }

}
