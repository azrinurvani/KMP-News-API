package com.azrinurvani.kmp_news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.azrinurvani.kmp_news.data.repository.LocalNewsRepository
import com.azrinurvani.kmp_news.theme.NewsTheme
import com.azrinurvani.kmp_news.ui.navigation.graphs.RootNavGraph
import com.azrinurvani.kmp_news.ui.setting.SettingViewModel
import com.azrinurvani.kmp_news.utils.AppPreferences
import com.azrinurvani.kmp_news.utils.dataStorePreferences
import com.azrinurvani.kmp_news.utils.getDatabaseBuilder
import com.azrinurvani.kmp_news.utils.getRoomDatabase
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    val appPreferences = remember {
        AppPreferences(
            dataStore = dataStorePreferences()
        )
    }

    val newsDao = remember {
        getRoomDatabase(getDatabaseBuilder()).newsDao()
    }

    val settingViewModel = viewModel {
        SettingViewModel(
            appPreferences = appPreferences,
            localNewsRepository = LocalNewsRepository(newsDao = newsDao)
        )
    }
    val currentTheme by settingViewModel.currentTheme.collectAsState()



    NewsTheme(
        currentTheme
    ) {
       RootNavGraph(
           settingViewModel = settingViewModel,
           newsDao = newsDao
       )
    }
}