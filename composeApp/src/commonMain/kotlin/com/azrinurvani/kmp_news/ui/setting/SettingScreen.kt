package com.azrinurvani.kmp_news.ui.setting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController
){
    Box(
        modifier = modifier.fillMaxSize()
    ){
        Button(onClick = {
            rootNavController.navigateUp()
        }){
            Text(
                text = "Back",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Setting Screen",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}