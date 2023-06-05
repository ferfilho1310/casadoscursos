package br.com.casadoscursos.view.adapterCursos

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import br.com.casadoscursos.databinding.CursoItemBinding
import br.com.casadoscursos.models.Cursos.Curso
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class CursosViewHolder(val binding: CursoItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(curso: Curso, listener: AdapterCursos.CursoListener?) {
        binding.tvNameCurso.text = curso.titleCurso
        binding.subTextItem.text = curso.subtitleCurso

        binding.apply {
            tvPrecoCurso.text = curso.precoCurso
            tvPrecoCurso.isVisible = curso.precoCurso != null

            textView.isVisible =  curso.precoCurso != null
        }

        binding.button.setOnClickListener {
            listener?.onClickCurso(curso.linkCurso)
        }
    }

    fun setImageCurso(curso: Curso) {
        binding.pgItemCurso.isVisible = true
        binding.imgCurso.isVisible = false

        Picasso
            .get()
            .load(curso.imageCurso)
            .resize(1000, 800)
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