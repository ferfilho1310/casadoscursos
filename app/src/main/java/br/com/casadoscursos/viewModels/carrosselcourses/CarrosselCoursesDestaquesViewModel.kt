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

    private val _destaqueEducacao: MutableLiveData<Response<ArrayList<Cursos.Curso>>> =
        MutableLiveData()
    val destaqueEducacao: LiveData<Response<ArrayList<Cursos.Curso>>> = _destaqueEducacao

    private val _destaqueCulinaria: MutableLiveData<Response<ArrayList<Cursos.Curso>>> =
        MutableLiveData()
    val destaqueCulinaria: LiveData<Response<ArrayList<Cursos.Curso>>> = _destaqueCulinaria

    private val _destaqueBeleza: MutableLiveData<Response<ArrayList<Cursos.Curso>>> =
        MutableLiveData()
    val destaqueBeleza: LiveData<Response<ArrayList<Cursos.Curso>>> = _destaqueBeleza

    override fun searchDestaqueCarrossel(collectionName: String) {
        service.searchCoursesCarrossel(collectionName)
            .onEach {
                _carrosselcourses.value = Response.SUCCESS(it)
            }.catch {
                _carrosselcourses.value = Response.ERROR()
            }.launchIn(viewModelScope)
    }

    override fun searchDestaqueCarrosselCulinaria(collectionName: String) {
        service.searchCoursesCulinaria(collectionName)
            .onEach {
                _destaqueCulinaria.value = Response.SUCCESS(it)
            }.catch {
                _destaqueCulinaria.value = Response.ERROR()
            }.launchIn(viewModelScope)
    }

    override fun searchDestaqueCarrosselEducacao(collectionName: String) {
        service.searchCoursesEducacao(collectionName)
            .onEach {
                _destaqueEducacao.value = Response.SUCCESS(it)
            }.catch {
                _destaqueEducacao.value = Response.ERROR()
            }.launchIn(viewModelScope)
    }

    override fun searchDestaqueCarrosselBeleza(collectionName: String) {
        service.searchCoursesBeleza(collectionName)
            .onEach {
                _destaqueBeleza.value = Response.SUCCESS(it)
            }.catch {
                _destaqueBeleza.value = Response.ERROR()
            }.launchIn(viewModelScope)
    }
}