package com.azrinurvani.kmp_news.di

import com.azrinurvani.kmp_news.utils.AppPreferences
import com.azrinurvani.kmp_news.utils.dataStorePreferences
import com.azrinurvani.kmp_news.utils.getDatabaseBuilder
import com.azrinurvani.kmp_news.utils.getRoomDatabase
import org.koin.dsl.module

val databaseModule = module {
    //database
    single {
        getRoomDatabase(getDatabaseBuilder())
    }

    //data store
    single {
        AppPreferences(
            dataStore = dataStorePreferences()
        )
    }
}