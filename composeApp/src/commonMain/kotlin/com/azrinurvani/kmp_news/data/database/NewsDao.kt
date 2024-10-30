package com.azrinurvani.kmp_news.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.azrinurvani.kmp_news.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    @Query("SELECT * FROM article_table")
    fun getArticles() : Flow<List<Article>>

    @Query("SELECT * FROM article_table WHERE article_id = :articleId")
    suspend fun getArticle(articleId: String) : Article?

    @Delete
    suspend fun delete(article: Article)

    @Query("DELETE FROM article_table")
    suspend fun deleteAllArticle()
}