package com.azrinurvani.kmp_news.ui.bookmark

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.azrinurvani.kmp_news.data.database.NewsDao
import com.azrinurvani.kmp_news.data.repository.LocalNewsRepository
import com.azrinurvani.kmp_news.ui.common.ArticleListScreen
import com.azrinurvani.kmp_news.ui.common.EmptyContent
import com.azrinurvani.kmp_news.ui.common.ShimmerEffect
import com.azrinurvani.kmp_news.utils.getDatabaseBuilder
import com.azrinurvani.kmp_news.utils.getRoomDatabase
import kmp_news.composeapp.generated.resources.Res
import kmp_news.composeapp.generated.resources.ic_browse
import kmp_news.composeapp.generated.resources.ic_network_error
import kmp_news.composeapp.generated.resources.no_news
import org.jetbrains.compose.resources.stringResource

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    newsDao: NewsDao
){
    val bookmarkViewModel = viewModel{
        BookmarkViewModel(
            localNewsRepository = LocalNewsRepository(newsDao = newsDao)
        )
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
                EmptyContent(
                    message = stringResource(Res.string.no_news),
                    icon = Res.drawable.ic_browse,
                    isOnRetryBtnVisible = false
                )
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
        onError = { errorMessage->
            EmptyContent(
                message = errorMessage,
                icon = Res.drawable.ic_network_error,
                onRetryClicked = {
                    bookmarkViewModel.getHeadline()
                }
            )
        }
    )
}