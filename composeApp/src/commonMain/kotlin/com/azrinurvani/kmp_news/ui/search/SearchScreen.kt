package com.azrinurvani.kmp_news.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.azrinurvani.kmp_news.di.koinViewModel
import com.azrinurvani.kmp_news.theme.mediumPadding
import com.azrinurvani.kmp_news.ui.common.ArticleListScreen
import com.azrinurvani.kmp_news.ui.common.EmptyContent
import com.azrinurvani.kmp_news.ui.common.ShimmerEffect
import com.azrinurvani.kmp_news.ui.search.components.SearchBar
import kmp_news.composeapp.generated.resources.Res
import kmp_news.composeapp.generated.resources.ic_browse
import kmp_news.composeapp.generated.resources.ic_network_error
import kmp_news.composeapp.generated.resources.no_news
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavController
){
    var searchQuery by rememberSaveable { mutableStateOf("") }

    val searchViewModel = koinViewModel<SearchViewModel>()

    val uiState by searchViewModel.newsStateFlow.collectAsState()


   Column(
       modifier = modifier,
       verticalArrangement = Arrangement.spacedBy(mediumPadding)
   ) {

       SearchBar(
           text = searchQuery,
           onValueChange = {
               searchQuery = it
//               searchViewModel.searchQuery(it)
           },
           onSearch = { query->
               if (query.trim().isNotEmpty()) {
                   println("searchQuery : $query")
                   searchViewModel.searchQuery(query)
               }
           },
       )

       uiState.DisplayResult(
           onIdle = {
               EmptyContent(
                   message = stringResource(Res.string.no_news),
                   icon = Res.drawable.ic_browse,
                   isOnRetryBtnVisible = false
               )
           },
           onLoading = {
               ShimmerEffect()
           },
           onSuccess = { articlesList->
               if (articlesList.isEmpty()){
                   EmptyContent(
                       message = stringResource(Res.string.no_news),
                       icon = Res.drawable.ic_browse
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
                       if (searchQuery.trim().isNotEmpty()) {
                           searchViewModel.searchQuery(searchQuery)
                       }
                   }
               )
           }
       )
   }
}