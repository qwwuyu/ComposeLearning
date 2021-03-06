package com.qwwuyu.compose.platform

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.qwwuyu.base.SelectDialog
import com.qwwuyu.compose.module.widget.SelectTab
import com.qwwuyu.base.utils.WLog

@Composable
actual fun WinApi() {
    Column(Modifier.fillMaxSize()) {
        val text = SelectTab(
            listOf("DropdownMenu", "CDialog", "CSelect=3", "CSelect=10")
        )
        Box(Modifier.weight(1f)) {
            when (text) {
                "DropdownMenu" -> CDropdownMenu()
                "CDialog" -> CDialog()
                "CSelect=3" -> CSelect(3)
                "CSelect=10" -> CSelect(10)
            }
        }
    }
}

/* ========================  ======================== */
@Composable
fun CDropdownMenu() {
    var isExpand by remember { mutableStateOf(true) }
    DropdownMenu(isExpand, onDismissRequest = { isExpand = false }) {
        DropdownMenuItem(onClick = { isExpand = false }) {
            Text("123")
        }
        DropdownMenuItem(onClick = { isExpand = false }) {
            Text("234")
        }
        Text("456")
    }
}

/* ========================  ======================== */
@Composable
fun CDialog() {
    var isShow by remember { mutableStateOf(true) }
    if (isShow) {
        Dialog(onDismissRequest = { WLog.i("onDismissRequest") }) {
            Button(
                onClick = { isShow = false }
            ) {
                Icon(Icons.Default.Close, null)
            }
        }
    }
}

/* ========================  ======================== */
@Composable
fun CSelect(size: Int) {
    var isShow by remember { mutableStateOf(true) }
    if (isShow) {
        val selects = (0..size).map { "index$it" }.toList()
        SelectDialog(onDismiss = {
            isShow = false
            WLog.i("select=$it")
        }, selects)
    }
}