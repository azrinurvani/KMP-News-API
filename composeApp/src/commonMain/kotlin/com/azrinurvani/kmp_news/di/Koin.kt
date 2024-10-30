package com.azrinurvani.kmp_news.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    appDeclaration: KoinAppDeclaration = {}
) =
    startKoin {
        appDeclaration()
        modules(
            networkModule, //will access firstly and make it to the top of module
            databaseModule,
            repositoryModule,
            viewModelModule
        )
    }