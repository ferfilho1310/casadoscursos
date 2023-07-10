package br.com.casadoscursos.repository.carrorreslrepository

import br.com.casadoscursos.models.Cursos
import kotlinx.coroutines.flow.Flow

interface CarrosselDestaquesRepositoryContract {

    fun searchCoursesCarrossel(collectionName: String) : Flow<ArrayList<Cursos.Curso>>
    fun searchCoursesBeleza(collectionName: String) : Flow<ArrayList<Cursos.Curso>>
    fun searchCoursesEducacao(collectionName: String) : Flow<ArrayList<Cursos.Curso>>
    fun searchCoursesCulinaria(collectionName: String) : Flow<ArrayList<Cursos.Curso>>
}