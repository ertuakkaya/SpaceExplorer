package com.ertugrulakkaya.spaceexplorer.di


import androidx.room.RoomDatabase
import com.ertugrulakkaya.spaceexplorer.data.local.LaunchDatabase
import com.ertugrulakkaya.spaceexplorer.data.local.datasource.LaunchLocalDataSource
import com.ertugrulakkaya.spaceexplorer.data.local.datasource.LaunchLocalDataSourceImpl
import com.ertugrulakkaya.spaceexplorer.data.remote.datasource.LaunchRemoteDataSource
import com.ertugrulakkaya.spaceexplorer.data.remote.datasource.LaunchRemoteDataSourceImpl
import com.ertugrulakkaya.spaceexplorer.data.repository.LaunchRepositoryImpl
import com.ertugrulakkaya.spaceexplorer.domain.repository.LaunchRepository
import com.ertugrulakkaya.spaceexplorer.domain.usecase.ObserveLaunchesUseCase
import com.ertugrulakkaya.spaceexplorer.domain.usecase.RefreshLaunchesUseCase
import com.ertugrulakkaya.spaceexplorer.presentation.launch_detail.DetailViewModel
import com.ertugrulakkaya.spaceexplorer.presentation.launch_list.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val sharedModule = module {
    // DB
    single<LaunchDatabase> {
        val builder =
            get<RoomDatabase.Builder<LaunchDatabase>>()
        builder
            .fallbackToDestructiveMigration(true)
            .build()
    }
    single { get<LaunchDatabase>().launchDao() }

    // DataSource
    single<LaunchLocalDataSource> { LaunchLocalDataSourceImpl(get())}
    single<LaunchRemoteDataSource> { LaunchRemoteDataSourceImpl(get()) }

    // Repository
    single <LaunchRepository>{ LaunchRepositoryImpl(get(), get()) }

    // UseCase
    single { ObserveLaunchesUseCase(get()) }
    single { RefreshLaunchesUseCase(get()) }

    // Viewmodel
    viewModel { HomeViewModel(get(), get())  }
    viewModel { DetailViewModel(get()) }


}