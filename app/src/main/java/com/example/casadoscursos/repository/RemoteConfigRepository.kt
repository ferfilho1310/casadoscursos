package com.example.casadoscursos.repository

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.casadoscursos.models.CursoCulinaria
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class RemoteConfigRepository : RemoteConfigRepositoryContract {

    val frc = FirebaseRemoteConfig.getInstance()

    override fun remoteConfigFetch(context: Context?, categoryCursoRemoteConfig: String): Flow<CursoCulinaria> {
        return callbackFlow {
            val gson = Gson()
            val listener = frc.fetchAndActivate()
                .addOnCompleteListener(context as Activity) { task ->
                    if(task.isSuccessful) {
                        val adsListRemoteConfig = frc.getString(categoryCursoRemoteConfig)
                        if (adsListRemoteConfig.isNotEmpty()) {
                            val ads = gson.fromJson(adsListRemoteConfig, CursoCulinaria::class.java)
                            trySend(ads).isSuccess
                        } else {
                            trySend(CursoCulinaria()).isFailure
                            Log.e("Error", "Erro ao fazer o fetch no remote config ${task.exception}")
                        }
                    }
                }
            awaitClose {
                listener.isCanceled
            }
        }
    }
}