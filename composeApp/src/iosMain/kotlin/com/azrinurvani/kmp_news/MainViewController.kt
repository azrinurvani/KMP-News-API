package com.azrinurvani.kmp_news

import androidx.compose.ui.window.ComposeUIViewController
import com.azrinurvani.kmp_news.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }