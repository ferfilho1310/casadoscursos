package br.com.casadoscursos.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.casadoscursos.helpers.Response
import br.com.casadoscursos.models.Cursos.Curso
import br.com.casadoscursos.repository.searchcourses.SearchCoursesRepositoryContract
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchCoursesViewModel(
    val repository: SearchCoursesRepositoryContract
) : ViewModel(),
    SearchCoursesViewModelContract {

    private val _searchCoursesBeleza: MutableLiveData<Response<ArrayList<Curso>>> =
        MutableLiveData()
    var searchCoursesBeleza: LiveData<Response<ArrayList<Curso>>> =
        _searchCoursesBeleza

    private val _searchCoursesCulinaria: MutableLiveData<Response<ArrayList<Curso>>> =
        MutableLiveData()
    var searchCoursesCulinaria: LiveData<Response<ArrayList<Curso>>> =
        _searchCoursesCulinaria

    private val _searchCoursesEducacao: MutableLiveData<Response<ArrayList<Curso>>> =
        MutableLiveData()
    var searchCoursesEducacao: LiveData<Response<ArrayList<Curso>>> =
        _searchCoursesEducacao

    private val _remoteconfigBemEstar: MutableLiveData<Response<ArrayList<Curso>>> =
        MutableLiveData()
    var remoteconfigBemEstar: LiveData<Response<ArrayList<Curso>>> =
        _remoteconfigBemEstar

    override fun searchCoursesEducacao(context: Context?, categoryCursoRemoteConfig: String) {
        _searchCoursesEducacao.value =
            Response.LOADING()
        repository.remoteConfigFetchEducacao(context, categoryCursoRemoteConfig)
            .onEach {
                _searchCoursesEducacao.value =
                    Response.SUCCESS(it)
            }.catch {
                _searchCoursesEducacao.value = Response.ERROR()
            }.launchIn(viewModelScope)
    }

    override fun searchCoursesBemEstar(context: Context?, categoryCursoRemoteConfig: String) {
        _remoteconfigBemEstar.value = Response.LOADING()
        repository.remoteConfigFetchBemEstar(context, categoryCursoRemoteConfig)
            .onEach {
                _remoteconfigBemEstar.value =
                    Response.SUCCESS(it)
            }.catch {
                _remoteconfigBemEstar.value = Response.ERROR()
            }.launchIn(viewModelScope)
    }

    override fun searchCoursesBeleza(context: Context?, categoryCursoRemoteConfig: String) {
        _searchCoursesBeleza.value = Response.LOADING()
        repository.remoteConfigFetchBeleza(context, categoryCursoRemoteConfig)
            .onEach {
                _searchCoursesBeleza.value = Response.SUCCESS(it)
            }.catch {
                _searchCoursesBeleza.value = Response.ERROR()
            }.launchIn(viewModelScope)
    }

    override fun searchCoursesCulinaria(context: Context?, categoryCursoRemoteConfig: String) {
        _searchCoursesCulinaria.value = Response.LOADING()
        repository.remoteConfigFetchCulinaria(context, categoryCursoRemoteConfig)
            .onEach {
                _searchCoursesCulinaria.value =
                    Response.SUCCESS(it)
            }.catch {
                _searchCoursesCulinaria.value = Response.ERROR()
            }.launchIn(viewModelScope)
    }
}