package br.com.casadoscursos.repository

import android.content.Context
import br.com.casadoscursos.models.Cursos
import kotlinx.coroutines.flow.Flow

interface RemoteConfigRepositoryContract {
    fun remoteConfigFetchBeleza(
        context: Context?,
        categoryCursoRemoteConfig: String
    ): Flow<ArrayList<Cursos.Curso>>

    fun remoteConfigFetchCulinaria(
        context: Context?,
        categoryCursoRemoteConfig: String
    ): Flow<ArrayList<Cursos.Curso>>

    fun remoteConfigFetchEducacao(
        context: Context?,
        categoryCursoRemoteConfig: String
    ): Flow<ArrayList<Cursos.Curso>>

    fun remoteConfigFetchBemEstar(
        context: Context?,
        categoryCursoRemoteConfig: String
    ): Flow<ArrayList<Cursos.Curso>>
}