package br.com.casadoscursos.viewModels

import android.content.Context

interface RemoteConfigViewModelContract {

    fun remoteConfigFetch(context: Context?, categoryCursoRemoteConfig: String)
    fun remoteConfigFetchCulinaria(context: Context?, categoryCursoRemoteConfig: String)
    fun remoteConfigFetchEducacao(context: Context?, categoryCursoRemoteConfig: String)
    fun remoteConfigFetchBemEstar(context: Context?, categoryCursoRemoteConfig: String)
}