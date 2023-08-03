package br.com.casadoscursos.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cursos(
    val cursos: ArrayList<Curso> = arrayListOf()
) : Parcelable {
    @Parcelize
    data class Curso(
        val id: String? = null,
        val linkCurso: String? = null,
        val titleCurso: String? = null,
        val imageCurso: String? = null,
        val subtitleCurso: String? = null,
        val precoCurso: String? = null,
        val descriptionCourse: String? = null
    ) : Parcelable
}
