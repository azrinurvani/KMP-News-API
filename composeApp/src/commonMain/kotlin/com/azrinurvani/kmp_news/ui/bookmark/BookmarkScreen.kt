package com.azrinurvani.kmp_news.ui.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.azrinurvani.kmp_news.ui.common.ArticleListScreen
import com.azrinurvani.kmp_news.ui.common.EmptyContent
import com.azrinurvani.kmp_news.ui.common.ShimmerEffect
import com.azrinurvani.kmp_news.ui.headline.HeadlineViewModel

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    navController: NavController
){
    val bookmarkViewModel = viewModel{
        HeadlineViewModel()
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