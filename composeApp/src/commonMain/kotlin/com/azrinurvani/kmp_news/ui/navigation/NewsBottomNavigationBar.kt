package com.azrinurvani.kmp_news.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NewsBottomNavigationBar(
    bottomNavigationItemList : List<BottomNavigationItem>,
    currentRoute : String?,
    onItemClick : (BottomNavigationItem) -> Unit
){
    NavigationBar(
    ) {
        bottomNavigationItemList.forEach{ navigationItem ->
            NavigationBarItem(
                selected = currentRoute == navigationItem.route,
                onClick = {
                    onItemClick(navigationItem)
                },
                icon = {
                    Icon(
                        painter = painterResource(navigationItem.icon),
                        contentDescription = stringResource(navigationItem.title)
                    )
                },
                label = {
                    Text(
                        text = stringResource(navigationItem.title),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            )
        }
    }
}