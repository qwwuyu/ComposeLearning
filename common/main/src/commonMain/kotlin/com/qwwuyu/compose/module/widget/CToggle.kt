package com.qwwuyu.compose.module.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class CToggle {
    companion object {
        @Composable
        fun main() {
            CToggleMain()
        }
    }
}

@Composable
fun CToggleMain() {
    Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
        var checked by remember { mutableStateOf(true) }
        Checkbox(
            checked = checked,
            modifier = Modifier.padding(8.dp),
            onCheckedChange = { checked = !checked }
        )

        var switched by remember { mutableStateOf(true) }
        Switch(
            checked = switched,
            colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colors.primary),
            modifier = Modifier.padding(8.dp),
            onCheckedChange = { switched = it }
        )

        Column {
            var selected by remember { mutableStateOf("Kotlin") }
            for (lang in arrayOf("Kotlin", "Java", "Swift")) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = selected == lang, onClick = { selected = lang })
                    Text(
                        text = lang,
                        modifier = Modifier.clickable(onClick = { selected = lang }).padding(start = 4.dp)
                    )
                }
            }
        }

        var sliderState by remember { mutableStateOf(0f) }
        Slider(value = sliderState, modifier = Modifier.fillMaxWidth().padding(8.dp),
            onValueChange = { newValue ->
                sliderState = newValue
            }
        )

        var sliderState2 by remember { mutableStateOf(20f) }
        Slider(value = sliderState2, modifier = Modifier.fillMaxWidth().padding(8.dp),
            valueRange = 0f..100f,
            steps = 4,
            colors = SliderDefaults.colors(thumbColor = MaterialTheme.colors.secondary),
            onValueChange = { newValue ->
                sliderState2 = newValue
            }
        )
    }
}