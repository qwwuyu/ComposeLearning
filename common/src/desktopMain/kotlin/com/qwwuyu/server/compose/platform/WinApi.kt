package com.qwwuyu.server.compose.platform

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SettingsPhone
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.RenderVectorGroup
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import com.qwwuyu.server.compose.module.test.SelectTab
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import java.nio.file.Path

@Composable
actual fun WinApi() {
    Column(Modifier.fillMaxSize()) {
        val text = SelectTab(listOf("Window"))
        Box(Modifier.weight(1f)) {
            when (text) {
                "Window" -> CWindow()
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CWindow() {
    var exit by remember { mutableStateOf(true) }
    var trayState by mutableStateOf(true)
    var menuState by remember { mutableStateOf(true) }
    var file by remember { mutableStateOf("选择文件") }
    var saveState by remember { mutableStateOf("保存文件") }

    LaunchedEffect(Unit) {
        notifications.collect { saveState = it }
    }
    if (exit) {
        val fileDialog = DialogState<Path?>()
        val saveDialog = DialogState<Path?>()
        val exitDialog = DialogState<AlertDialogResult>()

        val scope = rememberCoroutineScope()
        fun exit() = scope.launch { exit = ask(exitDialog) }
        fun file() = scope.launch { file = fileDialog.awaitResult()?.toFile()?.absolutePath ?: "选择文件" }
        fun save() = scope.launch {
            val path = saveDialog.awaitResult()
            if (path != null) {
                val saveJob = path.launchSaving("123")
                try {
                    saveJob.join()
                    _notifications.trySend("保存成功")
                } catch (e: Exception) {
                    _notifications.trySend("保存失败:" + e.message)
                } finally {
                    saveJob.cancel()
                }
            }
        }

        val tray = TrayState()
        val state = WindowState()
        val image = Icons.Default.SettingsPhone
        val painter = rememberVectorPainter(
            defaultWidth = image.defaultWidth,
            defaultHeight = image.defaultHeight,
            viewportWidth = image.viewportWidth,
            viewportHeight = image.viewportHeight,
            name = image.name,
            tintColor = Color(0xFF2CA4E1),
            tintBlendMode = image.tintBlendMode,
            content = { _, _ -> RenderVectorGroup(group = image.root) }
        )


        Window(
            state = state,
            title = "title",
            icon = painter,
            onCloseRequest = { exit() }
        ) {
            if (trayState) {
                Tray(painter, state = tray, hint = "hint qwwuyu", menu = { ApplicationMenu { exit() } })
            }

            if (menuState) {
                WindowMenuBar { exit() }
            }

            Column(Modifier.fillMaxSize()) {
                Text("全屏控制", Modifier.clickable {
                    state.placement = if (state.placement == WindowPlacement.Fullscreen)
                        WindowPlacement.Floating else WindowPlacement.Fullscreen
                })
                Text("退出", Modifier.clickable { exit() })
                Text(file, Modifier.clickable { file() })
                Text(saveState, Modifier.clickable { save() })
                Text("菜单", Modifier.clickable { menuState = !menuState })
                Text("托盘", Modifier.clickable { trayState = !trayState }.height(0.dp))
                Text("托盘通知",
                    Modifier.clickable { tray.sendNotification(Notification("title", "msg", Notification.Type.Info)) })
            }

            if (fileDialog.isAwaiting) {
                FileDialog(
                    title = "选择文件title",
                    isLoad = true,
                    onResult = { fileDialog.onResult(it) }
                )
            }

            if (saveDialog.isAwaiting) {
                FileDialog(
                    title = "保存文件title",
                    isLoad = false,
                    onResult = { saveDialog.onResult(it) }
                )
            }

            if (exitDialog.isAwaiting) {
                YesNoCancelDialog(
                    title = "title",
                    message = "是否退出",
                    onResult = { exitDialog.onResult(it) }
                )
            }
        }
    }
}

var _notifications = Channel<String>(0)
val notifications: Flow<String> get() = _notifications.receiveAsFlow()

private suspend fun ask(exitDialog: DialogState<AlertDialogResult>): Boolean {
    return when (exitDialog.awaitResult()) {
        AlertDialogResult.Yes -> false
        AlertDialogResult.No -> true
        AlertDialogResult.Cancel -> true
    }
}

class DialogState<T> {
    private var onResult: CompletableDeferred<T>? by mutableStateOf(null)

    val isAwaiting get() = onResult != null

    suspend fun awaitResult(): T {
        onResult = CompletableDeferred()
        val result = onResult!!.await()
        onResult = null
        return result
    }

    fun onResult(result: T) = onResult!!.complete(result)
}

@Composable
private fun MenuScope.ApplicationMenu(exit: () -> Unit) {
    Item("Exit1", onClick = { exit() })
    Separator()
    Item("Exit2", onClick = { exit() })
}

@Composable
private fun FrameWindowScope.WindowMenuBar(exit: () -> Unit) = MenuBar {
    Menu("Menu1") {
        Item("Exit", onClick = { exit() })
        Item("Exit", onClick = { exit() })
    }
    Menu("Settings") {
        Item("Exit1", onClick = { exit() })
        Separator()
        Item("Exit2", onClick = { exit() })
    }
}

private fun Path.launchSaving(text: String) = GlobalScope.launch {
    writeTextAsync(text)
}

private suspend fun Path.writeTextAsync(text: String) = withContext(Dispatchers.IO) {
    toFile().writeText(text)
}