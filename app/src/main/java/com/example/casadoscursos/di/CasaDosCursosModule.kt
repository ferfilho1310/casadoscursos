package com.example.casadoscursos.di

import com.example.casadoscursos.repository.RemoteConfigRepository
import com.example.casadoscursos.repository.RemoteConfigRepositoryContract
import com.example.casadoscursos.viewModels.RemoteConfigViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object CasaDosCursosModule {

    val instance = module {
        viewModel { RemoteConfigViewModel(get())}
        factory<RemoteConfigRepositoryContract> { RemoteConfigRepository() }
    }
}