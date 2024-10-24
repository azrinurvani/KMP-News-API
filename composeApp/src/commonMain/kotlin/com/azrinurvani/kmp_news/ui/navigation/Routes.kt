package com.azrinurvani.kmp_news.ui.navigation


object Graph{
    const val RootScreenGraph = "rootScreenGraph"
    const val MainScreenGraph = "mainScreenGraph"
}

//For Handling Screen of Bottom Navigation
sealed class MainRouteScreen(
    val route: String
){
    object Headline : MainRouteScreen("headline")
    object Search : MainRouteScreen("search")
    object Bookmark : MainRouteScreen("bookmark")

}

sealed class SettingRouteScreen(
    val route: String
){
    object Setting : SettingRouteScreen("setting")
}