package br.com.casadoscursos.view.adapterCursos

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.casadoscursos.databinding.CursoItemBinding
import br.com.casadoscursos.models.Cursos.Curso

class AdapterCursos : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var context: Context? = null
    var listCursos: ArrayList<Curso>? = arrayListOf()
    var listener: CursoListener? = null

    fun setCursos(context: Context, cursos: ArrayList<Curso>?, listener: CursoListener) {
        listCursos?.clear()
        listCursos?.addAll(cursos.orEmpty())
        this.context = context
        this.listener = listener
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
            viewHolder.bind(it[position], listener)
            viewHolder.setImageCurso(it[position])
        }
    }

    interface CursoListener {
        fun onClickCurso(urlAffiliate: String)
    }
}