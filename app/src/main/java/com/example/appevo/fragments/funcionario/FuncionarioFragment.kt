package com.example.appevo.fragments.funcionario


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.appevo.R
import com.example.appevo.adapters.AdapterFunc
import com.example.appevo.model.Departamento
import com.example.appevo.model.Funcionario
import kotlinx.android.synthetic.main.fragment_funcionario.view.*

class FuncionarioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View = inflater!!.inflate(R.layout.fragment_funcionario, container, false)

        val activity = activity as Context
        val recyclerViewDepto = view.findViewById<RecyclerView>(R.id.recyclerViewFunc)

        recyclerViewDepto.layoutManager = LinearLayoutManager(activity)
        recyclerViewDepto.adapter = AdapterFunc(listaFunc(), activity)

        view.buttonAddDepto.setOnClickListener { view ->
            val updateFuncionarioFragment = UpdateFuncionarioFragment()
            replaceFragment(updateFuncionarioFragment)

        }

        return view
    }

    fun replaceFragment(fragment: Fragment) {
        fragmentManager!!.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun listaFunc(): List<Funcionario> {
        val resut : MutableList<Funcionario>  = mutableListOf<Funcionario>()
        for (x in 0 until 30 step 1){
            resut.add(x, Funcionario(x,"Funcionario  - $x",x,Departamento(x, "Deparatamento - $x","DPTO - $x")))
        }

        return resut

    }


}
