package com.azrinurvani.kmp_news.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.azrinurvani.kmp_news.data.repository.LocalNewsRepository
import com.azrinurvani.kmp_news.utils.AppPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SettingViewModel (
    private val appPreferences: AppPreferences,
    private val localNewsRepository: LocalNewsRepository
): ViewModel() {

    private val _currentTheme : MutableStateFlow<String?> = MutableStateFlow(null)
    val currentTheme : StateFlow<String?> = _currentTheme.asStateFlow()

    init {
        currentThemeGet()
    }

    fun deleteAllBookmark(){
        viewModelScope.launch(Dispatchers.IO) {
            localNewsRepository.deleteAllArticle()
        }
    }

    private fun currentThemeGet() = runBlocking {
        //use runBlocking because when using viewModelScope.launch it will take a half a second to process a data
        //and actually my currentTheme have to set by default, and it will be execute by default first before process data
        // in scope
        _currentTheme.update {
            appPreferences.getTheme()
        }
    }

    fun changeThemeMode(value:String){
        viewModelScope.launch(Dispatchers.IO) {
            appPreferences.changeTheme(value)
            _currentTheme.update {
                value
            }
        }

    }
}