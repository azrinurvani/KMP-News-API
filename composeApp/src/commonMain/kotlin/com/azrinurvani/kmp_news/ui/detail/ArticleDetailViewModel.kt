package com.azrinurvani.kmp_news.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azrinurvani.kmp_news.data.model.Article
import com.azrinurvani.kmp_news.data.repository.LocalNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ArticleDetailViewModel(
    private val localNewsRepository: LocalNewsRepository
) : ViewModel(){

    fun bookmarkArticle(currentArticle: Article){
        viewModelScope.launch (Dispatchers.IO){
            localNewsRepository.upsertArticle(article = currentArticle)
        }
    }
}