package com.example.appevo.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appevo.model.Departamento
import kotlinx.android.synthetic.main.activity_adapter_depto.view.*

class AdapterDepto(
    private val deptoList: List<Departamento>,
    private val context: Context
) : RecyclerView.Adapter<AdapterDepto.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(com.example.appevo.R.layout.activity_adapter_depto, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return deptoList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val depto = deptoList[p1]
        p0.bind(depto)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nome = itemView.textViewAdapterDepto
        val sigla = itemView.textViewAdapterSigla

        fun bind(departamento: Departamento) {
            nome.text = departamento.nome
            sigla.text = departamento.sigla
        }
    }

    interface RecyclerViewClickListener {
        fun onClick(view: View, position: Int)
    }
}