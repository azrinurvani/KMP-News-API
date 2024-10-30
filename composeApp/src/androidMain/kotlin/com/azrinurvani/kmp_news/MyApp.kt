package com.azrinurvani.kmp_news

import android.app.Application
import com.azrinurvani.kmp_news.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent

class MyApp : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidLogger()
            androidContext(this@MyApp)
        }
    }
}