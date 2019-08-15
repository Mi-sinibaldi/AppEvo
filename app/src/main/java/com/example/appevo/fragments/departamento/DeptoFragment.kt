package com.example.appevo.fragments.departamento

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.appevo.R
import com.example.appevo.adapters.AdapterDepto
import kotlinx.android.synthetic.main.fragment_depto.*

class DeptoFragment : Fragment() {

    lateinit var adapterDepto: AdapterDepto
    lateinit var deptoList: List<AdapterDepto>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_depto, container, false)

        adapterDepto = AdapterDepto(deptoList, DeptoFragment.class.java)
        recyclerViewDepto.adapter = AdapterDepto
        recyclerViewDepto.layoutManager = LinearLayoutManager(this)
        recyclerViewDepto.smoothScrollToPosition(deptoList.size)




    }

}
