package com.example.appevo.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appevo.R
import com.example.appevo.model.Funcionario
import kotlinx.android.synthetic.main.activity_adapter_func.view.*

class AdapterFunc(
    private val listFunc: List<Funcionario>,
    private val context: Context) : RecyclerView.Adapter<AdapterFunc.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_adapter_func, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFunc.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val func = listFunc[p1]
        p0.nomeFunc.text
        p0.rgFunc.text
        p0.deptoFunc.text

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nomeFunc = itemView.textViewNomeAdapterFunc
        val rgFunc = itemView.textViewRgAdapter
        val deptoFunc = itemView.textViewDeptoAdapter
    }

}