package br.com.casadoscursos.repository.carrorreslrepository

import br.com.casadoscursos.models.Cursos
import kotlinx.coroutines.flow.Flow

interface CarrosselDestaquesRepositoryContract {

    fun searchCoursesCarrossel() : Flow<ArrayList<Cursos.Curso>>
}