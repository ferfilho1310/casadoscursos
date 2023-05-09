package com.example.casadoscursos.repository

import android.content.Context
import com.example.casadoscursos.models.CursoCulinaria
import kotlinx.coroutines.flow.Flow

interface RemoteConfigRepositoryContract {
    fun remoteConfigFetch(
        context: Context?,
        categoryCursoRemoteConfig: String
    ): Flow<CursoCulinaria>
}