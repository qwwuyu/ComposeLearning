package com.qwwuyu.compose.module.kv

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.qwwuyu.base.platform.VerticalScrollbar
import com.qwwuyu.tkv.KVItem
import com.qwwuyu.tkv.MKV

@Composable
fun KVContent(component: MKV) {
    val model by component.models.subscribeAsState()

    Column {
        TopAppBar(
            title = { Text("MKV") },
            navigationIcon = {
                IconButton(onClick = component::finish) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )

        Box(Modifier.weight(1F)) {
            KVList(
                items = model.items,
                onItemClicked = component::onItemClicked,
                onDeleteItemClicked = component::onItemDeleteClicked
            )
        }
    }
}

@Composable
private fun KVList(
    items: List<KVItem>,
    onItemClicked: (KVItem) -> Unit,
    onDeleteItemClicked: (String) -> Unit
) {
    Box {
        val listState = rememberLazyListState()
        LazyColumn(state = listState) {
            items(items) {
                KVItem(it, onItemClicked, onDeleteItemClicked)
                Divider()
            }
        }
        VerticalScrollbar(
            Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            listState
        )
    }
}

@Composable
private fun KVItem(
    item: KVItem,
    onItemClicked: (KVItem) -> Unit,
    onDeleteItemClicked: (String) -> Unit
) {
    Text(text = "${item.key} = ${item.value}",
        fontSize = 16.sp,
        modifier = Modifier.padding(horizontal = 8.dp)
            .clickable { onItemClicked(item) }
    )
}