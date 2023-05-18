package com.example.casadoscursos.view.adapterCursos

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.casadoscursos.databinding.CursoItemBinding
import com.example.casadoscursos.models.Cursos
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.supercharge.shimmerlayout.ShimmerLayout
import java.lang.Exception

class CursosViewHolder(val binding: CursoItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(curso: Cursos.Curso, listener: AdapterCursos.CursoListener?) {
        binding.tvNameCurso.text = curso.titleCurso
        binding.subTextItem.text = curso.subtitleCurso
        binding.button.setOnClickListener {
            listener?.onClickCurso(curso.linkCurso)
        }
    }

    fun setImageCurso(curso: Cursos.Curso) {
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