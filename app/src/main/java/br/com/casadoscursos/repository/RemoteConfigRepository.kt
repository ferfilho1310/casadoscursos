package br.com.casadoscursos.repository

import android.app.Activity
import android.content.Context
import android.util.Log
import br.com.casadoscursos.models.Cursos
import br.com.casadoscursos.models.TitlesCursos
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class RemoteConfigRepository : RemoteConfigRepositoryContract {

    val frc = FirebaseRemoteConfig.getInstance()

    override fun remoteConfigFetchBemEstar(
        context: Context?,
        categoryCursoRemoteConfig: String
    ): Flow<Cursos> {
        return callbackFlow {
            val gson = Gson()
            val listener = frc.fetch(0)
                .addOnCompleteListener(context as Activity) { task ->
                    if (task.isSuccessful) {
                        val adsListRemoteConfig = frc.getString(categoryCursoRemoteConfig)
                        if (adsListRemoteConfig.isNotEmpty()) {
                            val ads = gson.fromJson(adsListRemoteConfig, Cursos::class.java)
                            trySend(ads).isSuccess
                            Log.i("Json Cursos", "$ads")
                        } else {
                            trySend(Cursos()).isFailure
                            Log.e(
                                "Error",
                                "Erro ao fazer o fetch no remote config ${task.exception}"
                            )
                        }
                    }
                }
            awaitClose {
                listener.isCanceled
            }
        }
    }


    override fun remoteConfigFetch(
        context: Context?,
        categoryCursoRemoteConfig: String
    ): Flow<Cursos> {
        return callbackFlow {
            val gson = Gson()
            val listener = frc.fetch(0)
                .addOnCompleteListener(context as Activity) { task ->
                    if (task.isSuccessful) {
                        val adsListRemoteConfig = frc.getString(categoryCursoRemoteConfig)
                        if (adsListRemoteConfig.isNotEmpty()) {
                            val ads = gson.fromJson(adsListRemoteConfig, Cursos::class.java)
                            trySend(ads).isSuccess
                            Log.i("Json Cursos", "$ads")
                            frc.fetchAndActivate()
                        } else {
                            trySend(Cursos()).isFailure
                            Log.e(
                                "Error",
                                "Erro ao fazer o fetch no remote config ${task.exception}"
                            )
                        }
                    }
                }.addOnFailureListener {

                    trySend(Cursos()).isFailure
                    Log.e(
                        "Error",
                        "Erro ao fazer o fetch no remote config $it"
                    )
                }
            awaitClose {
                listener.isCanceled
            }
        }
    }

    override fun remoteConfigFetchCulinaria(
        context: Context?,
        categoryCursoRemoteConfig: String
    ): Flow<Cursos> {
        return callbackFlow {
            val gson = Gson()
            val listener = frc.fetch(0)
                .addOnCompleteListener(context as Activity) { task ->
                    if (task.isSuccessful) {
                        val adsListRemoteConfig = frc.getString(categoryCursoRemoteConfig)
                        if (adsListRemoteConfig.isNotEmpty()) {
                            val ads = gson.fromJson(adsListRemoteConfig, Cursos::class.java)
                            trySend(ads).isSuccess
                            Log.i("Json Cursos", "$ads")
                        } else {
                            trySend(Cursos()).isFailure
                            Log.e(
                                "Error",
                                "Erro ao fazer o fetch no remote config ${task.exception}"
                            )
                        }
                    }
                }
            awaitClose {
                listener.isCanceled
            }
        }
    }

    override fun remoteConfigFetchEducacao(
        context: Context?,
        categoryCursoRemoteConfig: String
    ): Flow<Cursos> {
        return callbackFlow {
            val gson = Gson()
            val listener = frc.fetch(0)
                .addOnCompleteListener(context as Activity) { task ->
                    if (task.isSuccessful) {
                        val adsListRemoteConfig = frc.getString(categoryCursoRemoteConfig)
                        if (adsListRemoteConfig.isNotEmpty()) {
                            val ads = gson.fromJson(adsListRemoteConfig, Cursos::class.java)
                            trySend(ads).isSuccess
                            Log.i("Json Cursos", "$ads")
                        } else {
                            trySend(Cursos()).isFailure
                            Log.e(
                                "Error",
                                "Erro ao fazer o fetch no remote config ${task.exception}"
                            )
                        }
                    }
                }
            awaitClose {
                listener.isCanceled
            }
        }
    }

    override fun remoteConfigFetchTitles(
        context: Context?,
        paramRemoteConfig: String
    ): Flow<TitlesCursos> {
        return callbackFlow {
            val gson = Gson()
            val listener = frc.fetch(0)
                .addOnCompleteListener(context as Activity) { task ->
                    if (task.isSuccessful) {
                        val adsListRemoteConfig = frc.getString(paramRemoteConfig)
                        if (adsListRemoteConfig.isNotEmpty()) {
                            val ads = gson.fromJson(adsListRemoteConfig, TitlesCursos::class.java)
                            trySend(ads).isSuccess
                        } else {
                            trySend(TitlesCursos()).isFailure
                            Log.e(
                                "Error",
                                "Erro ao fazer o fetch no remote config ${task.exception}"
                            )
                        }
                    }
                }
            awaitClose {
                listener.isCanceled
            }
        }
    }
}