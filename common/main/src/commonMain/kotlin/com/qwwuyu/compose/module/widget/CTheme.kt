package com.qwwuyu.compose.module.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qwwuyu.compose.theme.ColorPallet
import com.qwwuyu.compose.theme.localTheme
import com.qwwuyu.compose.theme.wColors

class CTheme {
    companion object {
        @Composable
        fun main() {
            CThemeMain()
        }
    }
}

@Composable
fun CThemeMain() {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        val localTheme = localTheme()
        ThemeCompose("Dark") { localTheme(true, null) }
        ThemeCompose("Light") { localTheme(false, null) }
        ThemeCompose("BLUE") { localTheme(null, ColorPallet.BLUE) }
        ThemeCompose("PURPLE") { localTheme(null, ColorPallet.PURPLE) }
        ThemeCompose("GREEN") { localTheme(null, ColorPallet.GREEN) }
        ThemeCompose("ORANGE") { localTheme(null, ColorPallet.ORANGE) }
        Box(
            modifier = Modifier.fillMaxWidth().height(48.dp)
                .background(color = MaterialTheme.wColors.color1)
        ) {
            Text(
                text = "custom color", modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(16.dp, 0.dp),
                color = MaterialTheme.wColors.color2
            )
        }
    }
}

@Composable
fun ThemeCompose(title: String, onItemClicked: (type: String) -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth().height(48.dp)
            .background(color = MaterialTheme.colors.primaryVariant)
            .clickable {
                onItemClicked(title)
            }
    ) {
        Text(
            text = title, modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(16.dp, 0.dp),
            color = MaterialTheme.colors.primary
        )
    }
}