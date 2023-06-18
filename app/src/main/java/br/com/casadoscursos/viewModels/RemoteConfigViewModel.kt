package br.com.casadoscursos.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.casadoscursos.helpers.Response
import br.com.casadoscursos.models.Cursos.Curso
import br.com.casadoscursos.repository.RemoteConfigRepositoryContract
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RemoteConfigViewModel(
    val repository: RemoteConfigRepositoryContract
) : ViewModel(),
    RemoteConfigViewModelContract {

    private val _remoteconfig: MutableLiveData<Response<ArrayList<Curso>>> =
        MutableLiveData()
    var remoteconfig: LiveData<Response<ArrayList<Curso>>> =
        _remoteconfig

    private val _remoteconfigCulinaria: MutableLiveData<Response<ArrayList<Curso>>> =
        MutableLiveData()
    var remoteconfigCulinaria: LiveData<Response<ArrayList<Curso>>> =
        _remoteconfigCulinaria

    private val _remoteconfigEducacao: MutableLiveData<Response<ArrayList<Curso>>> =
        MutableLiveData()
    var remoteconfigEducacao: LiveData<Response<ArrayList<Curso>>> =
        _remoteconfigEducacao

    private val _remoteconfigBemEstar: MutableLiveData<Response<ArrayList<Curso>>> =
        MutableLiveData()
    var remoteconfigBemEstar: LiveData<Response<ArrayList<Curso>>> =
        _remoteconfigBemEstar

    override fun remoteConfigFetchEducacao(context: Context?, categoryCursoRemoteConfig: String) {
        _remoteconfigEducacao.value =
            Response.LOADING()
        repository.remoteConfigFetchEducacao(context, categoryCursoRemoteConfig)
            .onEach {
                _remoteconfigEducacao.value =
                    Response.SUCCESS(it)
            }.catch {
                _remoteconfigEducacao.value = Response.ERROR()
            }.launchIn(viewModelScope)
    }

    override fun remoteConfigFetchBemEstar(context: Context?, categoryCursoRemoteConfig: String) {
        _remoteconfigBemEstar.value = Response.LOADING()
        repository.remoteConfigFetchBemEstar(context, categoryCursoRemoteConfig)
            .onEach {
                _remoteconfigBemEstar.value =
                    Response.SUCCESS(it)
            }.catch {
                _remoteconfigBemEstar.value = Response.ERROR()
            }.launchIn(viewModelScope)
    }

    override fun remoteConfigFetch(context: Context?, categoryCursoRemoteConfig: String) {
        _remoteconfig.value = Response.LOADING()
        repository.remoteConfigFetchBeleza(context, categoryCursoRemoteConfig)
            .onEach {
                _remoteconfig.value = Response.SUCCESS(it)
            }.catch {
                _remoteconfig.value = Response.ERROR()
            }.launchIn(viewModelScope)
    }

    override fun remoteConfigFetchCulinaria(context: Context?, categoryCursoRemoteConfig: String) {
        _remoteconfigCulinaria.value = Response.LOADING()
        repository.remoteConfigFetchCulinaria(context, categoryCursoRemoteConfig)
            .onEach {
                _remoteconfigCulinaria.value =
                    Response.SUCCESS(it)
            }.catch {
                _remoteconfigCulinaria.value = Response.ERROR()
            }.launchIn(viewModelScope)
    }
}