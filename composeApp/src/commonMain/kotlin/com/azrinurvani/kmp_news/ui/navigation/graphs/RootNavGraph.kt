package com.azrinurvani.kmp_news.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.azrinurvani.kmp_news.data.model.Article
import com.azrinurvani.kmp_news.ui.MainScreen
import com.azrinurvani.kmp_news.ui.detail.ArticleDetailScreen
import com.azrinurvani.kmp_news.ui.navigation.Graph
import com.azrinurvani.kmp_news.ui.navigation.NewsRouteScreen
import com.azrinurvani.kmp_news.ui.navigation.SettingRouteScreen
import com.azrinurvani.kmp_news.ui.setting.SettingScreen
import com.azrinurvani.kmp_news.ui.setting.SettingViewModel
import kotlinx.serialization.json.Json

@Composable
fun RootNavGraph(
    settingViewModel: SettingViewModel
){
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        route = Graph.RootScreenGraph,
        startDestination = Graph.MainScreenGraph
    ){
        composable(
            route = Graph.MainScreenGraph
        ){
            MainScreen(
                rootNavController = rootNavController
            )
        }

        composable(
            route = SettingRouteScreen.Setting.route
        ){
            SettingScreen(
                rootNavController = rootNavController,
                settingViewModel = settingViewModel
            )

        }

        composable(
            route = NewsRouteScreen.NewsDetail.route
        ){
            rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("article")?.let {
                val article : Article = Json.decodeFromString(it)
                ArticleDetailScreen(
                    navController = rootNavController,
                    currentArticle = article
                )
            }

        }
    }
}