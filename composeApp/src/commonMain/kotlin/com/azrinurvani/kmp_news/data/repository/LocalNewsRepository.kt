package com.azrinurvani.kmp_news.data.repository

import com.azrinurvani.kmp_news.data.database.NewsDao
import com.azrinurvani.kmp_news.data.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flowOn

class LocalNewsRepository(
    private val newsDao: NewsDao
) {

    suspend fun upsertArticle(article: Article){
        newsDao.upsert(article = article)
    }

    fun getArticles() = newsDao.getArticles().flowOn(Dispatchers.IO)

    suspend fun getArticle(articleId: String) = newsDao.getArticle(articleId = articleId)

    suspend fun deleteArticle(article: Article){
        newsDao.delete(article = article)
    }

    suspend fun deleteAllArticle(){
        newsDao.deleteAllArticle()
    }
}