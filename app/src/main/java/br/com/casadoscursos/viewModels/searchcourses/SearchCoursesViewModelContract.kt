package br.com.casadoscursos.viewModels.searchcourses

import android.content.Context

interface SearchCoursesViewModelContract {

    fun searchCoursesBeleza(context: Context?, categoryCursoRemoteConfig: String)
    fun searchCoursesCulinaria(context: Context?, categoryCursoRemoteConfig: String)
    fun searchCoursesEducacao(context: Context?, categoryCursoRemoteConfig: String)
    fun searchCoursesBemEstar(context: Context?, categoryCursoRemoteConfig: String)
}