package com.example.casadoscursos.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.casadoscursos.helpers.Response
import com.example.casadoscursos.models.CursoCulinaria
import com.example.casadoscursos.repository.RemoteConfigRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RemoteConfigViewModel(
    val repository: RemoteConfigRepository
) : ViewModel(),
    RemoteConfigViewModelContract {

    val _remoteconfig: MutableLiveData<Response<CursoCulinaria>> = MutableLiveData()
    var remoteconfig: LiveData<Response<CursoCulinaria>> = _remoteconfig


    override fun remoteConfigFetch(context: Context?, categoryCursoRemoteConfig: String) {
        repository.remoteConfigFetch(context, categoryCursoRemoteConfig)
            .onEach {
                _remoteconfig.value = Response.SUCCESS(it)
            }.catch {
                _remoteconfig.value = Response.ERROR()
            }.launchIn(viewModelScope)
    }
}