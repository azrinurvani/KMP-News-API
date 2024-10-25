package com.azrinurvani.kmp_news.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.azrinurvani.kmp_news.theme.mediumPadding
import com.azrinurvani.kmp_news.ui.common.ArticleListScreen
import com.azrinurvani.kmp_news.ui.search.components.SearchBar
import com.azrinurvani.kmp_news.utils.articles

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier
){
    var searchQuery by rememberSaveable { mutableStateOf("") }

   Column(
       modifier = modifier,
       verticalArrangement = Arrangement.spacedBy(mediumPadding)
   ) {

       SearchBar(
           text = searchQuery,
           onValueChange = {
               searchQuery = it
           },
           onSearch = { query->
               if (query.trim().isNotEmpty()) {
                   println("searchQuery : $query")
               }
           },
       )

       ArticleListScreen(articleList = articles)
   }
}