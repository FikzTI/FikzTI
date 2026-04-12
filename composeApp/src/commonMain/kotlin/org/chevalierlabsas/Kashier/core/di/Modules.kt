package org.chevalierlabsas.Kashier.core.di

import org.chevalierlabsas.Kashier.history.data.HistoryDataSource
import org.chevalierlabsas.Kashier.history.data.HistoryDataSourceImpl
import org.chevalierlabsas.Kashier.history.data.HistoryRepositoryImpl
import org.chevalierlabsas.Kashier.history.domain.repository.HistoryRepository
import org.chevalierlabsas.Kashier.history.presentation.HistoryViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val shareModules = module {
    // Registrasi DataSource
    singleOf(::HistoryDataSourceImpl).bind<HistoryDataSource>()

    // Registrasi Repository
    singleOf(::HistoryRepositoryImpl).bind<HistoryRepository>()

    // Registrasi ViewModel
    factoryOf(::HistoryViewModel)
}