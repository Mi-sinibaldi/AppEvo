package com.example.appevo.ui.fragments


import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*

import com.example.appevo.R
import com.example.appevo.infra.AppDatabase
import com.example.appevo.infra.dao.FuncionarioDao
import com.example.appevo.model.Departamento
import com.example.appevo.ui.adapters.AdapterFunc
import com.example.appevo.model.Funcionario
import com.example.appevo.ui.activity.DepartamentoInputActivity
import com.example.appevo.ui.activity.DepartamentoUpdateActivity
import com.example.appevo.ui.activity.FuncionarioInputActivity
import com.example.appevo.ui.activity.FuncionarioUpdateActivity
import kotlinx.android.synthetic.main.fragment_depto.view.*
import kotlinx.android.synthetic.main.fragment_funcionario.*
import kotlinx.android.synthetic.main.fragment_funcionario.view.*
import kotlinx.android.synthetic.main.fragment_funcionario.view.buttonAddFunc
import kotlinx.android.synthetic.main.fragment_funcionario.view.recyclerViewFunc

class FuncionarioFragment : Fragment() {

    lateinit var adapterFunc: AdapterFunc
    private lateinit var funcionarioDao: FuncionarioDao
    lateinit var recyclerViewFunc: RecyclerView
    private var funcionarios: List<Funcionario> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       val view = inflater!!.inflate(R.layout.fragment_funcionario, container, false)

        val activity = activity as Context

        val database = Room.databaseBuilder(
            activity,
            AppDatabase::class.java,
            "evo-app-database"
        )
            .allowMainThreadQueries()
            .build()

        funcionarioDao = database.funcionarioDao()
        funcionarios = listaFunc()
        recyclerViewFunc = view.findViewById<RecyclerView>(R.id.recyclerViewFunc)

        recyclerViewFunc.layoutManager = LinearLayoutManager(activity)
        recyclerViewFunc.adapter = AdapterFunc(funcionarios, activity)

        recyclerViewFunc!!.addOnItemTouchListener(
            RecyclerTouchListener(activity, recyclerViewFunc!!, object : ClickListener {

                override fun onClick(view: View, position: Int) {

                    val intent = Intent(getActivity(), FuncionarioUpdateActivity::class.java)
                    val funcionario = funcionarios?.get(position)

                    intent.putExtra("Id", funcionario.idFunc)
                    getActivity()?.startActivity(intent)
                }

                override fun onLongClick(view: View?, position: Int) {
                    val xxxx = position
                }
            })
        )

        view.buttonAddFunc.setOnClickListener {
            val intent = Intent(getActivity(), FuncionarioInputActivity::class.java)
            getActivity()?.startActivity(intent)
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        recyclerViewFunc.adapter = AdapterFunc(listaFunc(), this.activity!!.applicationContext)
    }

    private fun listaFunc(): List<Funcionario> {
        return funcionarioDao.getAll()
    }

    interface ClickListener {
        fun onClick(view: View, position: Int)

        fun onLongClick(view: View?, position: Int)
    }

    internal class RecyclerTouchListener(context: Context, recyclerView: RecyclerView,
        private val clickListener: ClickListener?) : RecyclerView.OnItemTouchListener {

        private val gestureDetector: GestureDetector

        init {
            gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val child = recyclerView.findChildViewUnder(e.x, e.y)
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child))
                    }
                }
            })
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child))
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }
    }

}
