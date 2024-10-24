package com.azrinurvani.kmp_news.utils

import com.azrinurvani.kmp_news.ui.navigation.BottomNavigationItem
import com.azrinurvani.kmp_news.ui.navigation.MainRouteScreen
import kmp_news.composeapp.generated.resources.Res
import kmp_news.composeapp.generated.resources.bookmark
import kmp_news.composeapp.generated.resources.headlines
import kmp_news.composeapp.generated.resources.ic_bookmark_outlined
import kmp_news.composeapp.generated.resources.ic_headline
import kmp_news.composeapp.generated.resources.ic_search
import kmp_news.composeapp.generated.resources.search

enum class Type{
    Mobile,
    Desktop
}

val bottomNavigationList = listOf(
    BottomNavigationItem(
        icon = Res.drawable.ic_headline,
        title = Res.string.headlines,
        route = MainRouteScreen.Headline.route
    ),
    BottomNavigationItem(
        icon = Res.drawable.ic_search,
        title = Res.string.search,
        route = MainRouteScreen.Search.route
    ),
    BottomNavigationItem(
        icon = Res.drawable.ic_bookmark_outlined,
        title = Res.string.bookmark,
        route = MainRouteScreen.Bookmark.route
    ),
)
