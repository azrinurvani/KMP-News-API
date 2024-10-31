package com.azrinurvani.kmp_news.ui.headline

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.azrinurvani.kmp_news.di.koinViewModel
import com.azrinurvani.kmp_news.theme.xSmallPadding
import com.azrinurvani.kmp_news.ui.common.ArticleListScreen
import com.azrinurvani.kmp_news.ui.common.EmptyContent
import com.azrinurvani.kmp_news.ui.common.ShimmerEffect
import com.azrinurvani.kmp_news.ui.navigation.SettingRouteScreen
import com.azrinurvani.kmp_news.utils.bottomNavigationList
import com.azrinurvani.kmp_news.utils.categoryList
import kmp_news.composeapp.generated.resources.Res
import kmp_news.composeapp.generated.resources.ic_browse
import kmp_news.composeapp.generated.resources.ic_network_error
import kmp_news.composeapp.generated.resources.no_news
import kmp_news.composeapp.generated.resources.setting
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeadlineScreen(
    modifier: Modifier = Modifier,
    navController : NavController,
    paddingValues: PaddingValues
){

    val headlineViewModel = koinViewModel<HeadlineViewModel>()

    val uiState by headlineViewModel.newsStateFlow.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        TopAppBar(
            title ={
                Text(
                    text = stringResource(bottomNavigationList[0].title),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            actions = {
                IconButton(
                    onClick = {
                        navController.navigate(route = SettingRouteScreen.Setting.route)
                    }
                ){
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = stringResource(Res.string.setting)
                    )
                }
            }
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = xSmallPadding),
            horizontalArrangement = Arrangement.spacedBy(xSmallPadding,Alignment.CenterHorizontally)
        ) {
            items(categoryList,key = {
                it
            }) { category ->
                FilterChip(
                    selected = headlineViewModel.category == category,
                    onClick = {
                        headlineViewModel.category = category
                        headlineViewModel.getHeadline()
                    },
                    label = {
                        Text(
                            text = category
                        )
                    }
                )
            }
        }

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
                        onRetryClicked = {
                            headlineViewModel.getHeadline()
                        }
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
                        headlineViewModel.getHeadline()
                    }
                )
            }
        )
    }



}