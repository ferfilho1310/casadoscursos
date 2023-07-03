package br.com.casadoscursos.viewModels.carrosselcourses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.casadoscursos.helpers.Response
import br.com.casadoscursos.models.Cursos
import br.com.casadoscursos.repository.carrorreslrepository.CarrosselDestaquesRepositoryContract
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CarrosselCoursesDestaquesViewModel(
    val service: CarrosselDestaquesRepositoryContract
) : ViewModel(),
    CarrosselCoursesDestaquesViewModelContract {

    private val _carrosselcourses: MutableLiveData<Response<ArrayList<Cursos.Curso>>> =
        MutableLiveData()
    val carrosselcourses: LiveData<Response<ArrayList<Cursos.Curso>>> = _carrosselcourses

    override fun searchCourseCarrossel() {
        service.searchCoursesCarrossel()
            .onEach {
                _carrosselcourses.value = Response.SUCCESS(it)
            }.catch {
                _carrosselcourses.value = Response.ERROR()
            }.launchIn(viewModelScope)
    }
}