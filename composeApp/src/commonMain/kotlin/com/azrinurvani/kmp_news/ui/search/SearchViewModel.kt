package com.azrinurvani.kmp_news.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azrinurvani.kmp_news.data.model.Article
import com.azrinurvani.kmp_news.utils.Resource
import com.azrinurvani.kmp_news.utils.articles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel(){
    private val _newsStateFlow =
        MutableStateFlow<Resource<List<Article>>>(Resource.Idle)

    val newsStateFlow : StateFlow<Resource<List<Article>>>
        get() = _newsStateFlow

    fun searchQuery(query:String) {
        viewModelScope.launch(Dispatchers.IO) {
            _newsStateFlow.emit(Resource.Loading)
            delay(2000)
            try {
//                val articleList = articles
                val articleList = articles
                _newsStateFlow.emit(Resource.Success(articleList))
            }catch (e:Exception){
                _newsStateFlow.emit(Resource.Error(e.message.toString()))
            }
        }
    }
}