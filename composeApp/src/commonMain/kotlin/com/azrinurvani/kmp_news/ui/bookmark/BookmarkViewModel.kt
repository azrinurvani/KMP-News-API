package com.azrinurvani.kmp_news.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azrinurvani.kmp_news.data.model.Article
import com.azrinurvani.kmp_news.data.repository.LocalNewsRepository
import com.azrinurvani.kmp_news.utils.Resource
import com.azrinurvani.kmp_news.utils.articles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class BookmarkViewModel(
    private val localNewsRepository: LocalNewsRepository
) : ViewModel(){

    private val _newsStateFlow =
        MutableStateFlow<Resource<List<Article>>>(Resource.Loading)

    val newsStateFlow : StateFlow<Resource<List<Article>>>
        get() = _newsStateFlow

    init {
        getHeadline()
    }

    fun getHeadline() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsStateFlow.emit(Resource.Loading)
            try {
                localNewsRepository.getArticles().catch {
                    it.printStackTrace()
                    _newsStateFlow.emit(Resource.Error(it.message.toString()))
                }.collect{ articleList->
                    _newsStateFlow.emit(Resource.Success(articleList))
                }
            }catch (e:Exception){
                _newsStateFlow.emit(Resource.Error(e.message.toString()))
            }
        }
    }
}