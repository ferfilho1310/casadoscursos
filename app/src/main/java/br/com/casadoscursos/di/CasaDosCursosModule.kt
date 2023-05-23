package br.com.casadoscursos.di

import br.com.casadoscursos.repository.RemoteConfigRepository
import br.com.casadoscursos.repository.RemoteConfigRepositoryContract
import br.com.casadoscursos.viewModels.RemoteConfigViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object CasaDosCursosModule {

    val instance = module {
        viewModel { RemoteConfigViewModel(get()) }
        factory<RemoteConfigRepositoryContract> { RemoteConfigRepository() }
    }
}