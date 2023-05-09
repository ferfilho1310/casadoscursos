package com.example.casadoscursos.models

data class CursoCulinaria(
    val enabled: Boolean = true,
    val categoriaCurso: String? = null,
    val cursos: List<Curso> = arrayListOf()
) {
    data class Curso(
        val linkCurso: String,
        val titleCurso: String,
        val imageCurso: String
    )
}
