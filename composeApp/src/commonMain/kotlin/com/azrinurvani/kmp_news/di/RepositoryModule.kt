package com.azrinurvani.kmp_news.di

import com.azrinurvani.kmp_news.data.database.NewsDatabase
import com.azrinurvani.kmp_news.data.repository.LocalNewsRepository
import com.azrinurvani.kmp_news.data.repository.OnlineNewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        OnlineNewsRepository(
            httpClient = get() //httpClient already define in NetworkModule and you can get the dependencies
        )
    }

    single {
        LocalNewsRepository(
            newsDao = get<NewsDatabase>().newsDao()
        )
    }
}