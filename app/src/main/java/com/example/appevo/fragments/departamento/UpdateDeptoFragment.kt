package com.example.appevo.fragments.departamento


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.appevo.R
import kotlinx.android.synthetic.main.fragment_update_depto.view.*

class UpdateDeptoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_update_depto, container, false)
        val view: View = inflater!!.inflate(R.layout.fragment_update_depto, container, false)
        view.buttonCancelar.setOnClickListener { view ->
            val fragment = DeptoFragment()
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
