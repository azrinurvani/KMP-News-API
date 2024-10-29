package com.azrinurvani.kmp_news.ui.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.azrinurvani.kmp_news.data.database.NewsDao
import com.azrinurvani.kmp_news.ui.bookmark.BookmarkScreen
import com.azrinurvani.kmp_news.ui.headline.HeadlineScreen
import com.azrinurvani.kmp_news.ui.navigation.Graph
import com.azrinurvani.kmp_news.ui.navigation.MainRouteScreen
import com.azrinurvani.kmp_news.ui.search.SearchScreen
import com.azrinurvani.kmp_news.utils.getDatabaseBuilder
import com.azrinurvani.kmp_news.utils.getRoomDatabase

@Composable
fun MainNavGraph(
    rootNavController: NavHostController,
    homeNavController: NavHostController,
    paddingValues: PaddingValues,
    newsDao : NewsDao
){

    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues),
        navController = homeNavController,
        route = Graph.MainScreenGraph,
        startDestination = MainRouteScreen.Headline.route
    ){
        composable(
            route = MainRouteScreen.Headline.route
        ){
            HeadlineScreen(
                navController = rootNavController
            )
        }

        composable(
            route = MainRouteScreen.Search.route
        ){
            SearchScreen(
                navController = rootNavController
            )
        }

        composable(
            route = MainRouteScreen.Bookmark.route
        ){
            BookmarkScreen(
                navController = rootNavController,
                newsDao = newsDao
            )
        }
    }
}