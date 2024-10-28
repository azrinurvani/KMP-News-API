package com.azrinurvani.kmp_news.ui.headline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azrinurvani.kmp_news.data.model.Article
import com.azrinurvani.kmp_news.data.model.ErrorResponse
import com.azrinurvani.kmp_news.data.model.NewsResponse
import com.azrinurvani.kmp_news.data.repository.OnlineNewsRepository
import com.azrinurvani.kmp_news.utils.Resource
import com.azrinurvani.kmp_news.utils.articles
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HeadlineViewModel(
    private val onlineNewsRepository: OnlineNewsRepository
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
            delay(2000)
            try {
//                val articleList = articles
                val httpResponse = onlineNewsRepository.getNews()

                if (httpResponse.status.value in 200..299){
                    val body = httpResponse.body<NewsResponse>()
                    _newsStateFlow.emit(Resource.Success(body.articles))
                }else{
                    val body = httpResponse.body<ErrorResponse>()
                    _newsStateFlow.emit(Resource.Error(body.message))
                }


            }catch (e:Exception){
                _newsStateFlow.emit(Resource.Error(e.message.toString()))
            }
        }
    }
}
