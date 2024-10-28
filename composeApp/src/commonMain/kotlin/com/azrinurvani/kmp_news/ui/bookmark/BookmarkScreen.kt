package com.azrinurvani.kmp_news.ui.bookmark

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.azrinurvani.kmp_news.ui.common.ArticleListScreen
import com.azrinurvani.kmp_news.ui.common.EmptyContent
import com.azrinurvani.kmp_news.ui.common.ShimmerEffect

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    navController: NavController
){
    val bookmarkViewModel = viewModel{
        BookmarkViewModel()
    }

    val uiState by bookmarkViewModel.newsStateFlow.collectAsState()
    uiState.DisplayResult(
        onIdle = {

        },
        onLoading = {
            ShimmerEffect()
        },
        onSuccess = { articlesList->
            if (articlesList.isEmpty()){
                EmptyContent("No data")
            }else{
                ArticleListScreen(
                    articleList = articlesList,
                    navController = navController
                )
            }
//            if (articlesList.isEmpty()){
//                EmptyContent("No data")
//            }else{
//                ArticleListScreen(articleList = articles)
//            }
        },
        onError = {
            EmptyContent(it)
        }
    )
}