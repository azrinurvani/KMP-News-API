package com.azrinurvani.kmp_news.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    var isBookmarked by mutableStateOf(false)

    fun isArticleBookmarked(currentArticle: Article){
        viewModelScope.launch(Dispatchers.IO) {
            localNewsRepository.getArticle(currentArticle.publishedAt)?.let {
                isBookmarked = true
            }
        }
    }

    fun bookmarkArticle(currentArticle: Article){
        viewModelScope.launch (Dispatchers.IO){
            if (!isBookmarked){
                localNewsRepository.upsertArticle(article = currentArticle)
            }else{
                localNewsRepository.deleteArticle(article = currentArticle)
            }
            isBookmarked = !isBookmarked
        }
    }
}