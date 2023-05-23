package br.com.casadoscursos.repository

import android.content.Context
import br.com.casadoscursos.models.Cursos
import br.com.casadoscursos.models.TitlesCursos
import kotlinx.coroutines.flow.Flow

interface RemoteConfigRepositoryContract {
    fun remoteConfigFetch(
        context: Context?,
        categoryCursoRemoteConfig: String
    ): Flow<Cursos>

    fun remoteConfigFetchTitles(
        context: Context?,
        paramRemoteConfig: String
    ): Flow<TitlesCursos>

    fun remoteConfigFetchCulinaria(
        context: Context?,
        categoryCursoRemoteConfig: String
    ): Flow<Cursos>

    fun remoteConfigFetchEducacao(
        context: Context?,
        categoryCursoRemoteConfig: String
    ): Flow<Cursos>

    fun remoteConfigFetchBemEstar(
        context: Context?,
        categoryCursoRemoteConfig: String
    ): Flow<Cursos>
}