package com.azrinurvani.kmp_news

import androidx.compose.runtime.Composable
import com.azrinurvani.kmp_news.theme.NewsTheme
import com.azrinurvani.kmp_news.ui.navigation.graphs.RootNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    NewsTheme {
       RootNavGraph()
    }
}