package com.azrinurvani.kmp_news.ui.headline

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.azrinurvani.kmp_news.ui.common.ArticleListScreen
import com.azrinurvani.kmp_news.utils.articles

@Composable
fun HeadlineScreen(
    modifier: Modifier = Modifier
){
   ArticleListScreen(articleList = articles)
}