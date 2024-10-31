package com.azrinurvani.kmp_news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.azrinurvani.kmp_news.di.koinViewModel
import com.azrinurvani.kmp_news.theme.NewsTheme
import com.azrinurvani.kmp_news.ui.MainScreen
import com.azrinurvani.kmp_news.ui.setting.SettingViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    val settingViewModel = koinViewModel<SettingViewModel>()
    val currentTheme by settingViewModel.currentTheme.collectAsState()

    NewsTheme(
        currentTheme
    ) {
        MainScreen(
            settingViewModel = settingViewModel
        )
    }
}