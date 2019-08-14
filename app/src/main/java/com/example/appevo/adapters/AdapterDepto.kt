package com.example.appevo.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appevo.R
import kotlinx.android.synthetic.main.activity_adapter_depto2.view.*


class AdapterDepto(private val deptoList: List<AdapterDepto>,
                   private val context: Context) : RecyclerView.Adapter<AdapterDepto.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.activity_adapter_depto2, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return deptoList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val depto = deptoList[p1]
        p0.nome.text
        p0.sigla.text
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nome = itemView.textViewAdapterDepto
        val sigla = itemView.textViewAdapterSigla



    }

}