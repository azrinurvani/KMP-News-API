package com.azrinurvani.kmp_news.ui.setting.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.azrinurvani.kmp_news.theme.mediumPadding
import com.azrinurvani.kmp_news.theme.xLargePadding
import com.azrinurvani.kmp_news.theme.xSmallPadding
import com.azrinurvani.kmp_news.utils.Theme
import kmp_news.composeapp.generated.resources.Res
import kmp_news.composeapp.generated.resources.apply
import kmp_news.composeapp.generated.resources.cancel
import kmp_news.composeapp.generated.resources.choose_a_theme
import kmp_news.composeapp.generated.resources.delete
import kmp_news.composeapp.generated.resources.delete_bookmark
import kmp_news.composeapp.generated.resources.delete_bookmark_description
import org.jetbrains.compose.resources.stringResource

@ExperimentalMaterial3Api
@Composable
fun DeleteBookmarkDialog(
    onDeleteBookmark : () -> Unit,
    onDismissRequest: () -> Unit
){

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = stringResource(Res.string.delete_bookmark),
                style = MaterialTheme.typography.titleMedium
            )
        },
        text = {
            Text(
                text = stringResource(Res.string.delete_bookmark_description),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDeleteBookmark()
                }
            ){
                Text(
                    text = stringResource(Res.string.delete)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ){
                Text(
                    text = stringResource(Res.string.cancel)
                )
            }
        }

    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelectionDialog(
    currentTheme : String,
    onThemeChange : (Theme) -> Unit,
    onDismissRequest : () -> Unit
){
    var currentSelectionTheme by remember{
        mutableStateOf(Theme.valueOf(currentTheme))

    }

    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        content = {
            Surface(
                modifier = Modifier.wrapContentSize(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(
                    modifier = Modifier.padding(mediumPadding)
                ) {
                    Text(
                        text = stringResource(Res.string.choose_a_theme),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(xSmallPadding)
                    )
                    Theme.entries.forEach { theme->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    currentSelectionTheme = theme
                                },
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = currentSelectionTheme == theme,
                                onClick = {
                                    currentSelectionTheme = theme
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colorScheme.primary,
                                    unselectedColor = MaterialTheme.colorScheme.onSurface
                                )
                            )

                            Text(
                                text = stringResource(theme.title)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(xLargePadding))
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ){
                        TextButton(
                            onClick = {
                                onDismissRequest()
                            }
                        ){
                            Text(
                                text = stringResource(Res.string.cancel)
                            )
                        }
                        Spacer(modifier = Modifier.width(mediumPadding))

                        TextButton(
                            onClick = {
                                onThemeChange(currentSelectionTheme)
                            }
                        ){
                            Text(
                                text = stringResource(Res.string.apply)
                            )
                        }
                        Spacer(modifier = Modifier.width(mediumPadding))
                    }
                }
            }
        }
    )
}

