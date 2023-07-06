package br.com.casadoscursos.view.fragments.destaques.carrossel

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import br.com.casadoscursos.databinding.CarrosselItemBinding
import br.com.casadoscursos.models.Cursos
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class DestaquesCarroselViewHolder(val binding: CarrosselItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(cursos: Cursos.Curso) {
        binding.apply {
            tvNameCurso.text = cursos.titleCurso
            tvPrecoCurso.text = cursos.precoCurso
        }
    }

    fun setOnClickListener(
        cursos: Cursos.Curso,
        listener: DestaquesCarroesselAdapter.CarrosselListener?
    ) {
        binding.carrosselItem.setOnClickListener {
            listener?.onClickItem(cursos.linkCurso.orEmpty())
        }
    }

    fun setImage(cursos: Cursos.Curso) {
        binding.pgItemCurso.isVisible = true
        binding.imgCurso.isVisible = false

        Picasso
            .get()
            .load(cursos.imageCurso)
            .resize(1000, 1000)
            .onlyScaleDown()
            .centerCrop()
            .into(binding.imgCurso, object : Callback {
                override fun onSuccess() {
                    binding.pgItemCurso.isVisible = false
                    binding.imgCurso.isVisible = true
                }

                override fun onError(e: Exception?) {

                }
            })
    }
}