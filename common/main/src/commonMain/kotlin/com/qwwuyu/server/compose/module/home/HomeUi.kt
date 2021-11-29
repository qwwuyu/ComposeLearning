package com.qwwuyu.server.compose.module.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.qwwuyu.home.MHome

@Composable
fun HomeContent(component: MHome) {
    val model by component.models.subscribeAsState()

    Column {
        TopAppBar(title = { Text(text = "Home") })

        Button(onClick = {
            component.onItemClicked("MKV")
        }, modifier = Modifier.padding(8.dp)) { Text(text = "MKV") }
    }
}