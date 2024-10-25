package com.azrinurvani.kmp_news.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.azrinurvani.kmp_news.theme.xLargePadding
import com.azrinurvani.kmp_news.utils.Type
import com.azrinurvani.kmp_news.utils.articles
import com.azrinurvani.kmp_news.utils.getRandomId
import com.azrinurvani.kmp_news.utils.getType


@Composable
fun ArticleListScreen(

){
    val isDesktop = remember {
        getType() == Type.Desktop
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(
            if(isDesktop) 3 else 1
        ),
        verticalArrangement = Arrangement.spacedBy(xLargePadding),
        horizontalArrangement = Arrangement.spacedBy(xLargePadding),
        contentPadding = PaddingValues(xLargePadding)
    ){
        items(articles, key = {
            it.publishedAt + getRandomId()
        }){ article->
            ArticleItem(
                article = article,
                onClick = {

                }
            )
        }
    }
}