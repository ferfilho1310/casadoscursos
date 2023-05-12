package com.example.casadoscursos.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.casadoscursos.helpers.Response
import com.example.casadoscursos.models.Cursos
import com.example.casadoscursos.repository.RemoteConfigRepository
import com.example.casadoscursos.repository.RemoteConfigRepositoryContract
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RemoteConfigViewModel(
    val repository: RemoteConfigRepositoryContract
) : ViewModel(),
    RemoteConfigViewModelContract {

    private val _remoteconfig: MutableLiveData<Response<ArrayList<Cursos.Curso>>> =
        MutableLiveData()
    var remoteconfig: LiveData<Response<ArrayList<Cursos.Curso>>> = _remoteconfig

    private val _remoteconfigTitles: MutableLiveData<Response<List<String>>> = MutableLiveData()
    var remoteconfigTitles: LiveData<Response<List<String>>> = _remoteconfigTitles

    override fun remoteConfigFetch(context: Context?, categoryCursoRemoteConfig: String) {
        repository.remoteConfigFetch(context, categoryCursoRemoteConfig)
            .onEach {
                _remoteconfig.value = Response.SUCCESS(it.cursos)
            }.catch {
                _remoteconfig.value = Response.ERROR()
            }.launchIn(viewModelScope)
    }

    override fun remoteConfigFetchTitles(context: Context?, paramRemoteConfig: String) {
        repository.remoteConfigFetchTitles(context, paramRemoteConfig)
            .onEach {
                _remoteconfigTitles.value = Response.SUCCESS(it.titles)
            }.catch {
                _remoteconfigTitles.value = Response.ERROR()
            }.launchIn(viewModelScope)
    }
}