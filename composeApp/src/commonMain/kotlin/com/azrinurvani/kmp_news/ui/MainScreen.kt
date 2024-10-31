package com.azrinurvani.kmp_news.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.azrinurvani.kmp_news.ui.navigation.NewsBottomNavigationBar
import com.azrinurvani.kmp_news.ui.navigation.graphs.RootNavGraph
import com.azrinurvani.kmp_news.ui.setting.SettingViewModel
import com.azrinurvani.kmp_news.utils.bottomNavigationList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    settingViewModel: SettingViewModel
){
    val rootNavController = rememberNavController()

    val navBackStackEntry by rootNavController.currentBackStackEntryAsState()


    val currentRoute by remember(navBackStackEntry){
        derivedStateOf { navBackStackEntry?.destination?.route }
    }
    val bottomNavRoute by remember{
        derivedStateOf {
            bottomNavigationList.find { it.route == currentRoute }
        }
    }

    val bottomBarVisibility by remember{
        derivedStateOf {
            bottomNavRoute != null
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarVisibility,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight }
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight }
                )
            ){
                NewsBottomNavigationBar(
                    bottomNavigationItemList = bottomNavigationList,
                    currentRoute = currentRoute,
                    onItemClick = { currentBottomNavigation->
//                    currentRoute = currentBottomNavigation.route
                        rootNavController.navigate(currentBottomNavigation.route){
                            rootNavController.graph.startDestinationRoute?.let {
                                popUpTo(it){
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }

        }
    ){ innerPadding ->
       RootNavGraph(
           rootNavController = rootNavController,
           innerPaddingValues = innerPadding,
           settingViewModel = settingViewModel
       )
    }
}