package com.example.appevo.ui.fragments

import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import com.example.appevo.R
import com.example.appevo.infra.AppDatabase
import com.example.appevo.infra.dao.DepartamentoDao
import com.example.appevo.ui.adapters.AdapterDepto
import com.example.appevo.model.Departamento
import com.example.appevo.ui.activity.DepartamentoInputActivity
import com.example.appevo.ui.activity.DepartamentoUpdateActivity
import com.example.appevo.ui.adapters.AdapterFunc
import kotlinx.android.synthetic.main.fragment_depto.*
import kotlinx.android.synthetic.main.fragment_depto.view.*
import kotlinx.android.synthetic.main.fragment_depto.view.recyclerViewDepartamento

class DeptoFragment : Fragment() {


    lateinit var adapterDepto: AdapterDepto
    private lateinit var departamentoDao: DepartamentoDao
    private var departamentos : List<Departamento> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view: View = inflater!!.inflate(R.layout.fragment_depto, container, false)

        val activity = activity as Context

        val database = Room.databaseBuilder(
            activity,
            AppDatabase::class.java,
            "evo-app-database")
            .allowMainThreadQueries()
            .build()

        departamentoDao = database.departamentoDao()
        departamentos = listaDeDeptos()
        val recyclerViewDepto = view.findViewById<RecyclerView>(R.id.recyclerViewDepartamento)

        recyclerViewDepto.layoutManager = LinearLayoutManager(activity)
        recyclerViewDepto.adapter = AdapterDepto(departamentos, activity)

        recyclerViewDepto!!.addOnItemTouchListener(RecyclerTouchListener(activity, recyclerViewDepto!!, object : ClickListener {

            override fun onClick(view: View, position: Int) {

                val intent = Intent (getActivity(), DepartamentoUpdateActivity::class.java)
                val departamento = departamentos?.get(position)

                intent.putExtra("Id",departamento.id)

                getActivity()?.startActivity(intent)
            }

            override fun onLongClick(view: View?, position: Int) {
                val xxxx = position
            }
        }))


        view.buttonAddDepartamento.setOnClickListener {
            val intent = Intent (getActivity(), DepartamentoInputActivity::class.java)
            getActivity()?.startActivity(intent)
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        recyclerViewDepartamento.adapter = AdapterDepto(listaDeDeptos(), this.activity!!.applicationContext)
    }

    private fun listaDeDeptos(): List<Departamento> {
        return  departamentoDao.getAll()
    }

    interface ClickListener {
        fun onClick(view: View, position: Int)

        fun onLongClick(view: View?, position: Int)
    }

    internal class RecyclerTouchListener(context: Context, recyclerView: RecyclerView, private val clickListener: ClickListener?) : RecyclerView.OnItemTouchListener {

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
