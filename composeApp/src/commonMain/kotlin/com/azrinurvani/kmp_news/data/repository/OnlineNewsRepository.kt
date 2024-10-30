package com.azrinurvani.kmp_news.data.repository

import com.azrinurvani.kmp_news.BuildKonfig
import com.azrinurvani.kmp_news.utils.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class OnlineNewsRepository(
    private val httpClient : HttpClient
) {

    suspend fun getNews(category : String) : HttpResponse{
        return httpClient.get{
            url("top-headlines")
            parameter("category",category)
            parameter("apiKey",BuildKonfig.API_KEY)
        }
    }

    suspend fun searchNews(query:String): HttpResponse{
        return httpClient.get{
            url("everything")
            parameter("q",query)
            parameter("apiKey",BuildKonfig.API_KEY)
        }
    }
}