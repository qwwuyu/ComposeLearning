package com.qwwuyu.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.rememberWindowState
import com.qwwuyu.base.gson.GsonHelper
import com.qwwuyu.base.utils.KVUtils
import com.qwwuyu.base.utils.WLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private var stateFlow: MutableStateFlow<Long>? = null
private var firstStateFlow = true
private val local = KVUtils.getValue("ws_local") ?: Local(
    width = 800f,
    height = 600f,
    x = 100f,
    y = 100f,
    placement = WindowPlacement.Floating.ordinal
)


@OptIn(FlowPreview::class)
@Composable
fun getLocalWindowState(): WindowState {
    val windowState = rememberWindowState(
        placement = WindowPlacement.values()[local.placement],
        position = WindowPosition(local.x.dp, local.y.dp),
        size = DpSize(local.width.dp, local.height.dp),
    )
    if (stateFlow == null) {
        val coroutineScope = rememberCoroutineScope()
        stateFlow = MutableStateFlow(0L).apply {
            coroutineScope.launch {
                withContext(Dispatchers.IO) {
                    debounce(1000).collect {
                        WLog.i("saveLocalWindowState=" + GsonHelper.toJson(local))
                        KVUtils.setValue("ws_local", local)
                    }
                }
            }
        }
    }
    WLog.i("getLocalWindowState=" + GsonHelper.toJson(local))
    LocalWindowState(windowState)
    return windowState
}

@Composable
fun LocalWindowState(windowState: WindowState) {
    local.width = windowState.size.width.value
    local.height = windowState.size.height.value
    local.x = windowState.position.x.value
    local.y = windowState.position.y.value
    local.placement = WindowPlacement.Floating.ordinal/*windowState.placement.ordinal*/
    if (firstStateFlow) {
        firstStateFlow = false
        return
    }
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        withContext(Dispatchers.IO) {
            stateFlow?.emit(System.currentTimeMillis())
        }
    }
}

private data class Local(var width: Float, var height: Float, var x: Float, var y: Float, var placement: Int)