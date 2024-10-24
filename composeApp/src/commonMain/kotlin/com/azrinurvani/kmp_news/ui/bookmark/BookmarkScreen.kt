package com.azrinurvani.kmp_news.ui.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
    ){
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Bookmark Screen",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}