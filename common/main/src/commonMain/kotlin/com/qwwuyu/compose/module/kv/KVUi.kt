package com.qwwuyu.compose.module.kv

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.qwwuyu.base.BaseDialog
import com.qwwuyu.base.platform.VerticalScrollbar
import com.qwwuyu.tkv.AEItem
import com.qwwuyu.tkv.KVItem
import com.qwwuyu.tkv.MKV

@Composable
fun KVContent(component: MKV) {
    val model by component.models.subscribeAsState()

    Column {
        TopAppBar(
            title = { Text("router,database,key-value") },
            navigationIcon = {
                IconButton(onClick = component::finish) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            },
            actions = {
                IconButton(onClick = component::onAddClicked) { Icon(Icons.Default.Add, contentDescription = null) }
            },
        )

        Box(Modifier.weight(1F)) {
            KVList(
                items = model.items,
                onItemClicked = component::onItemClicked,
                onDeleteItemClicked = component::onItemDeleteClicked
            )
        }
    }

    if (model.addItem.show) {
        AddDialog(model.addItem, component)
    } else if (model.editItem.show) {
        EditDialog(model.editItem, component)
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
    Surface(modifier = Modifier.clickable { onItemClicked(item) }) {
        @OptIn(ExperimentalMaterialApi::class)
        ListItem(
            text = { Text(item.key) },
            secondaryText = { Text(item.value) },
            trailing = {
                IconButton(onClick = { onDeleteItemClicked(item.key) }) {
                    Icon(Icons.Default.Delete, contentDescription = null)
                }
            }
        )
    }
}

@Composable
private fun AddDialog(addItem: AEItem, component: MKV) {
    BaseDialog({ component.onClose() }) {
        Card(
            modifier = Modifier.padding(32.dp).fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "添加键值",
                    style = typography.body1.copy(fontWeight = FontWeight.Bold),
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = addItem.key,
                    onValueChange = component::onKeyTextChanged,
                    modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
                    label = { Text(text = "键") },
                    placeholder = { Text(text = "输入键") },
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = addItem.value,
                    onValueChange = component::onValueTextChanged,
                    modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
                    label = { Text(text = "值") },
                    placeholder = { Text(text = "输入值") },
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = component::onConfirm,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).fillMaxWidth(),
                ) { Text(text = "确定") }
            }
        }
    }
}

@Composable
private fun EditDialog(editItem: AEItem, component: MKV) {
    BaseDialog({ component.onClose() }) {
        Card(
            modifier = Modifier.padding(32.dp).fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "修改键值",
                    style = typography.body1.copy(fontWeight = FontWeight.Bold),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "键：${editItem.key}",
                    style = typography.body2,
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = editItem.value,
                    onValueChange = component::onValueTextChanged,
                    modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
                    label = { Text(text = "值") },
                    placeholder = { Text(text = "输入值") },
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = component::onConfirm,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).fillMaxWidth(),
                ) { Text(text = "确定") }
            }
        }
    }
}