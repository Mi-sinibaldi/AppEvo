package com.example.appevo.fragments.funcionario

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appevo.R
import com.example.appevo.fragments.departamento.DeptoFragment
import kotlinx.android.synthetic.main.fragment_update_depto.view.*
import kotlinx.android.synthetic.main.fragment_update_funcionario.view.*


class UpdateFuncionarioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view: View = inflater!!.inflate(R.layout.fragment_update_funcionario, container, false)
        view.buttonCancelarFunc.setOnClickListener { view ->
            val fragment = FuncionarioFragment()
            replaceFragment(fragment)
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
}
