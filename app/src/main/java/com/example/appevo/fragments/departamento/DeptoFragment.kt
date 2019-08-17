package com.example.appevo.fragments.departamento

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appevo.R
import com.example.appevo.adapters.AdapterDepto
import com.example.appevo.model.Departamento
import kotlinx.android.synthetic.main.fragment_depto.view.*

class DeptoFragment : Fragment() {


    lateinit var adapterDepto: AdapterDepto

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view: View = inflater!!.inflate(R.layout.fragment_depto, container, false)

        val activity = activity as Context
        val recyclerViewDepto = view.findViewById<RecyclerView>(R.id.recyclerViewFunc)

        recyclerViewDepto.layoutManager = LinearLayoutManager(activity)
        recyclerViewDepto.adapter = AdapterDepto(listaDeDeptos(), activity)

        view.buttonAddDepto.setOnClickListener { view ->
            //Log.d("btnSetup", "Selected")
            val updateDeptoFragment = UpdateDeptoFragment()
            replaceFragment(updateDeptoFragment)
        }

        // Return the fragment view/layout
        return view
    }


    fun replaceFragment(fragment: Fragment) {
        fragmentManager!!.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .addToBackStack(null)
            .commit()
    }


    private fun listaDeDeptos(): List<Departamento> {
        return listOf(
            Departamento(1, "Tecnologia da Informação", "T.I"),
            Departamento(2, "Departamento Pessoal", "DP"),
            Departamento(3, "Recursos humanos", "RH"),
            Departamento(4, "Marketing", "Mkt"),
            Departamento(5, "Financeiro", "FI"),
            Departamento(6, "Vendas", "Vendas"),
            Departamento(7, "Compras", "Compras"),
            Departamento(8, "Diretoria", "Diretoria")
        )

    }

}
