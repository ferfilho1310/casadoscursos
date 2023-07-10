package br.com.casadoscursos.repository.carrorreslrepository

import br.com.casadoscursos.models.Cursos
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class CarrorreslDestaquesRepository : CarrosselDestaquesRepositoryContract {

    val frc = FirebaseFirestore.getInstance()

    override fun searchCoursesCarrossel(collectionName: String): Flow<ArrayList<Cursos.Curso>> {
        return callbackFlow {
            val listener = frc
                .collection(collectionName)
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

    override fun searchCoursesBeleza(collectionName: String): Flow<ArrayList<Cursos.Curso>> {
        return callbackFlow {
            val listener = frc
                .collection(collectionName)
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

    override fun searchCoursesEducacao(collectionName: String): Flow<ArrayList<Cursos.Curso>> {
        return callbackFlow {
            val listener = frc
                .collection(collectionName)
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

    override fun searchCoursesCulinaria(collectionName: String): Flow<ArrayList<Cursos.Curso>> {
        return callbackFlow {
            val listener = frc
                .collection(collectionName)
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