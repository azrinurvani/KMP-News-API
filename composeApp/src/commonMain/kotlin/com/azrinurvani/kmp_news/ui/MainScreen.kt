package com.azrinurvani.kmp_news.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.azrinurvani.kmp_news.data.database.NewsDao
import com.azrinurvani.kmp_news.ui.navigation.NewsBottomNavigationBar
import com.azrinurvani.kmp_news.ui.navigation.SettingRouteScreen
import com.azrinurvani.kmp_news.ui.navigation.graphs.MainNavGraph
import com.azrinurvani.kmp_news.utils.bottomNavigationList
import kmp_news.composeapp.generated.resources.Res
import kmp_news.composeapp.generated.resources.setting
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    rootNavController: NavHostController,
    newsDao : NewsDao
){
    val homeNavController = rememberNavController()

    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()

    var previousRoute by rememberSaveable{
        mutableStateOf(navBackStackEntry?.destination?.route)
    }

    val currentRoute by remember(navBackStackEntry){
        derivedStateOf { navBackStackEntry?.destination?.route }
    }

    val topBarTitle by remember(currentRoute) {
        derivedStateOf {
            if (currentRoute != null) {
                bottomNavigationList[bottomNavigationList.indexOfFirst {
                    it.route == currentRoute
                }].title
            }else{
                bottomNavigationList[0].title
            }

        }
    }

    DisposableEffect(Unit){
        onDispose {
            previousRoute = currentRoute
            println("previous route = $previousRoute")
        }
    }

    LaunchedEffect(Unit){
        if (previousRoute!=null){
            homeNavController.navigate(previousRoute!!){
                homeNavController.graph.startDestinationRoute?.let {
                    popUpTo(it){
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title ={
                    Text(
                        text = stringResource(topBarTitle),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            rootNavController.navigate(route = SettingRouteScreen.Setting.route)
                        }
                    ){
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = stringResource(Res.string.setting)
                        )
                    }
                }
            )
        },
        bottomBar = {
            NewsBottomNavigationBar(
                bottomNavigationItemList = bottomNavigationList,
                currentRoute = currentRoute,
                onItemClick = { currentBottomNavigation->
//                    currentRoute = currentBottomNavigation.route
                    homeNavController.navigate(currentBottomNavigation.route){
                        homeNavController.graph.startDestinationRoute?.let {
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
    ){ innerPadding ->
        MainNavGraph(
            rootNavController = rootNavController,
            homeNavController = homeNavController,
            paddingValues = innerPadding,
            newsDao = newsDao
        )
    }
}