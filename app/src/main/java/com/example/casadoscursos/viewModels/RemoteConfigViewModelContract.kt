package com.example.casadoscursos.viewModels

import android.content.Context

interface RemoteConfigViewModelContract {

    fun remoteConfigFetch(context: Context?, categoryCursoRemoteConfig: String)
    fun remoteConfigFetchTitles(context: Context?, paramRemoteConfig: String)
    fun remoteConfigFetchCulinaria(context: Context?, categoryCursoRemoteConfig: String)
    fun remoteConfigFetchEducacao(context: Context?, categoryCursoRemoteConfig: String)
    fun remoteConfigFetchBemEstar(context: Context?, categoryCursoRemoteConfig: String)
}