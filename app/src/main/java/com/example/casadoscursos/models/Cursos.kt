package com.example.casadoscursos.models

data class Cursos(
    val enabled: Boolean = true,
    val categoriaCurso: String? = null,
    val cursos: ArrayList<Curso> = arrayListOf()
) {
    data class Curso(
        val linkCurso: String,
        val titleCurso: String,
        val imageCurso: String
    )
}
