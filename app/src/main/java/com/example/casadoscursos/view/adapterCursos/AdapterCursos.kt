package com.example.casadoscursos.view.adapterCursos

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.casadoscursos.databinding.CursoItemBinding
import com.example.casadoscursos.models.Cursos

class AdapterCursos : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var context: Context? = null
    var listCursos: ArrayList<Cursos.Curso>? = arrayListOf()

    fun setCursos(context: Context, cursos: ArrayList<Cursos.Curso>?) {
        listCursos = cursos
        this.context = context
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CursosViewHolder(
            CursoItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listCursos?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as CursosViewHolder
        listCursos?.let {
            viewHolder.bind(it[position])
            viewHolder.setImageCurso(it[position])
        }
    }
}