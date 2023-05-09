package com.example.casadoscursos.viewModels

import android.content.Context

interface RemoteConfigViewModelContract {

    fun remoteConfigFetch(context: Context?, categoryCursoRemoteConfig: String)
}