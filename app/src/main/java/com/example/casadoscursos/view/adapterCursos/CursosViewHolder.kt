package com.example.casadoscursos.view.adapterCursos

import androidx.recyclerview.widget.RecyclerView
import com.example.casadoscursos.databinding.CursoItemBinding
import com.example.casadoscursos.models.Cursos
import com.squareup.picasso.Picasso

class CursosViewHolder(val binding: CursoItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(curso: Cursos.Curso) {
        binding.tvNameCurso.text = curso.titleCurso
    }

    fun setImageCurso(curso: Cursos.Curso) {
        Picasso
            .get()
            .load(curso.imageCurso)
            .centerCrop()
            .fit()
            .into(binding.imgCurso)
    }
}