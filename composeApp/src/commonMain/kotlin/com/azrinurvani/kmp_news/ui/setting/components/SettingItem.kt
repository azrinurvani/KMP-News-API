package com.azrinurvani.kmp_news.ui.setting.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import com.azrinurvani.kmp_news.theme.mediumPadding
import com.azrinurvani.kmp_news.theme.xLargePadding

@Composable
fun SettingItem(
    onClick: () -> Unit,
    painter: Painter,
    itemName : String,
    itemColor : Color = MaterialTheme.colorScheme.onSurface
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(mediumPadding)
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.spacedBy(mediumPadding,Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(xLargePadding),
            painter = painter,
            contentDescription = null,
            colorFilter = ColorFilter.tint(itemColor)
        )

        Text(
            text = itemName,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal),
            color = itemColor
        )
    }
}