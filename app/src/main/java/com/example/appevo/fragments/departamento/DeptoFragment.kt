package com.example.appevo.fragments.departamento

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.appevo.R

class DeptoFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_depto, container, false)

        val buttonAddDpto = view?.findViewById<Button>(R.id.buttonAddDpto)
        buttonAddDpto?.setOnClickListener {

        }

    }

}
