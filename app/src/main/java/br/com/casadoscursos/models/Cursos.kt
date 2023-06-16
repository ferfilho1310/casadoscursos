package br.com.casadoscursos.models

data class Cursos(
    val cursos: ArrayList<Curso> = arrayListOf()
) {
    data class Curso(
        val id: String? = null,
        val linkCurso: String? = null,
        val titleCurso: String? = null,
        val imageCurso: String? = null,
        val subtitleCurso: String? = null,
        val precoCurso: String? = null
    )
}
