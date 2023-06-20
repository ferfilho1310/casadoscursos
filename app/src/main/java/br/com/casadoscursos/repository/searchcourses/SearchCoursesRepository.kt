package br.com.casadoscursos.repository.searchcourses

import android.content.Context
import br.com.casadoscursos.models.Cursos
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SearchCoursesRepository : SearchCoursesRepositoryContract {

    val frc = FirebaseFirestore.getInstance()

    override fun remoteConfigFetchBemEstar(
        context: Context?,
        categoryCursoRemoteConfig: String
    ): Flow<ArrayList<Cursos.Curso>> {
        return callbackFlow {
            val listener = frc
                .collection("bemestar")
                .get()
                .addOnSuccessListener {
                    val belezaList = it.map { it.toObject(Cursos.Curso::class.java) }
                    trySend(belezaList as ArrayList).isSuccess
                }.addOnFailureListener {
                    trySend(arrayListOf()).isFailure
                }

            awaitClose {
                listener.isCanceled
            }
        }
    }


    override fun remoteConfigFetchBeleza(
        context: Context?,
        categoryCursoRemoteConfig: String
    ): Flow<ArrayList<Cursos.Curso>> {
        return callbackFlow {
            val listener = frc
                .collection("beleza")
                .get()
                .addOnSuccessListener {
                    val belezaList = it.map { it.toObject(Cursos.Curso::class.java) }
                    trySend(belezaList as ArrayList).isSuccess
                }.addOnFailureListener {
                    trySend(arrayListOf()).isFailure
                }

            awaitClose {
                listener.isCanceled
            }
        }
    }

    override fun remoteConfigFetchCulinaria(
        context: Context?,
        categoryCursoRemoteConfig: String
    ): Flow<ArrayList<Cursos.Curso>> {
        return callbackFlow {
            val listener = frc
                .collection("culinaria")
                .get()
                .addOnSuccessListener {
                    val belezaList = it.map { it.toObject(Cursos.Curso::class.java) }
                    trySend(belezaList as ArrayList).isSuccess
                }.addOnFailureListener {
                    trySend(arrayListOf()).isFailure
                }

            awaitClose {
                listener.isCanceled
            }
        }
    }

    override fun remoteConfigFetchEducacao(
        context: Context?,
        categoryCursoRemoteConfig: String
    ): Flow<ArrayList<Cursos.Curso>> {
        return callbackFlow {
            val listener = frc
                .collection("educacao")
                .get()
                .addOnSuccessListener {
                    val belezaList = it.map { it.toObject(Cursos.Curso::class.java) }
                    trySend(belezaList as ArrayList).isSuccess
                }.addOnFailureListener {
                    trySend(arrayListOf()).isFailure
                }

            awaitClose {
                listener.isCanceled
            }
        }
    }
}