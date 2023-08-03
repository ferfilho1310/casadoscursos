package br.com.casadoscursos.view.fragments.destaques.carrossel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.casadoscursos.databinding.CarrosselItemBinding
import br.com.casadoscursos.models.Cursos

class DestaquesCarroesselAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var cursosList: ArrayList<Cursos.Curso> = arrayListOf()
    var listener: CarrosselListener? = null

    fun setItemsCarroessel(items: ArrayList<Cursos.Curso>, listener: CarrosselListener?) {
        cursosList.clear()
        cursosList.addAll(items)
        this.listener = listener
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DestaquesCarroselViewHolder(
            CarrosselItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = cursosList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val carrosselHolder = holder as DestaquesCarroselViewHolder
        carrosselHolder.bind(cursosList[position])
        carrosselHolder.setImage(cursosList[position])
        carrosselHolder.setOnClickListener(cursosList[position], listener)
    }

    fun interface CarrosselListener {
        fun onClickItem(curso: Cursos.Curso)
    }
}