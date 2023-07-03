package br.com.casadoscursos.di

import br.com.casadoscursos.repository.carrorreslrepository.CarrorreslDestaquesRepository
import br.com.casadoscursos.repository.carrorreslrepository.CarrosselDestaquesRepositoryContract
import br.com.casadoscursos.repository.monitoringclickusers.CoursesMonitoring
import br.com.casadoscursos.repository.monitoringclickusers.CoursesMonitoringContract
import br.com.casadoscursos.repository.searchcourses.SearchCoursesRepository
import br.com.casadoscursos.repository.searchcourses.SearchCoursesRepositoryContract
import br.com.casadoscursos.viewModels.carrosselcourses.CarrosselCoursesDestaquesViewModel
import br.com.casadoscursos.viewModels.searchcourses.SearchCoursesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object CasaDosCursosModule {

    val instance = module {
        viewModel { SearchCoursesViewModel(get()) }
        viewModel {  CarrosselCoursesDestaquesViewModel(get()) }

        factory<SearchCoursesRepositoryContract> { SearchCoursesRepository() }
        factory<CoursesMonitoringContract> { CoursesMonitoring() }
        factory<CarrosselDestaquesRepositoryContract> { CarrorreslDestaquesRepository() }
    }
}