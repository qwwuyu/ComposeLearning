package com.qwwuyu.compose.platform

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.SettingsPhone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.RenderVectorGroup
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import com.qwwuyu.compose.module.widget.SelectTab
import com.qwwuyu.compose.utils.WLog
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import java.awt.Desktop
import java.io.File
import java.net.URI
import java.nio.file.Path

@Composable
actual fun WinApi() {
    Column(Modifier.fillMaxSize()) {
        val text = SelectTab(
            listOf(
                "Window", "Window2", "file", "splitPane",
                "desktop open", "desktop browse", "DropdownMenu", "CDialog",
            )
        )
        Box(Modifier.weight(1f)) {
            when (text) {
                "Window" -> CWindow()
                "Window2" -> Window(onCloseRequest = { }, undecorated = true) {}
                "file" -> CFile()
                "splitPane" -> @OptIn(ExperimentalSplitPaneApi::class) HorizontalSplitPane {
                    first(minSize = 30.dp) { Text("left") }
                    second { Text("right") }
                }
                "desktop open" -> Desktop.getDesktop().open(File("D://"))
                "desktop browse" -> Desktop.getDesktop().browse(URI.create("www.baidu.com"))
                "DropdownMenu" -> CDropdownMenu()
                "CDialog" -> CDialog()
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CWindow() {
    var exit by remember { mutableStateOf(true) }
    var menuState by remember { mutableStateOf(true) }
    var file by remember { mutableStateOf("选择文件") }
    var saveState by remember { mutableStateOf("保存文件") }
    var isVisible by remember { mutableStateOf(true) }

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
        val state = rememberWindowState()
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
            visible = isVisible,
            onCloseRequest = { exit() }
        ) {

            if (menuState) {
                WindowMenuBar { exit() }
            }

            Column(Modifier.fillMaxSize()) {
                Text("全屏控制", Modifier.clickable {
                    state.placement = when (state.placement) {
                        WindowPlacement.Fullscreen -> WindowPlacement.Maximized
                        WindowPlacement.Maximized -> WindowPlacement.Floating
                        WindowPlacement.Floating -> WindowPlacement.Fullscreen
                    }
                })
                Text("退出", Modifier.clickable { exit() })
                Text(file, Modifier.clickable { file() })
                Text(saveState, Modifier.clickable { save() })
                Text("菜单", Modifier.clickable { menuState = !menuState })
                Text("隐藏", Modifier.clickable { isVisible = !isVisible }.height(0.dp))
                Text("托盘通知",
                    Modifier.clickable { tray.sendNotification(Notification("title", "msg", Notification.Type.Info)) })

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(state.isMinimized, { state.isMinimized = !state.isMinimized })
                    Text("最小化")
                }
                Text(
                    "位移 ${state.position}",
                    Modifier.clickable {
                        val position = state.position
                        if (position is WindowPosition.Absolute) {
                            state.position = position.copy(x = state.position.x + 10.dp)
                        }
                    }
                )
                Text(
                    "大小 ${state.size}",
                    Modifier.clickable {
                        state.size = state.size.copy(width = state.size.width + 10.dp)
                    }
                )
                LaunchedEffect(state) {
                    snapshotFlow { state.size }
                        .onEach { println(it) }
                        .launchIn(this)

                    snapshotFlow { state.position }
//                        .filterNot { it.isSpecified }
                        .onEach { println(it) }
                        .launchIn(this)
                }
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

private var _notifications = Channel<String>(0)
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
        Menu("2级") {
            Item("Exit", onClick = { exit() })
            Item("Exit", onClick = { exit() })
        }
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

/* ========================  ======================== */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CFile() {
    Column(Modifier.verticalScroll(rememberScrollState()).fillMaxSize()) {
        val properties = System.getProperties()
        Text(properties.map { "${it.key} :: ${it.value}" }.joinToString("\n\n"))
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
        Dialog(onCloseRequest = { WLog.i("onCloseRequest") }) {
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
private fun ApplicationScope.ApplicationTray(state: TrayState) {
    Tray(
        icon = painterResource("drawable-nodpi/icon.png"),
        state = state,
        tooltip = "hint qwwuyu",
        menu = { })
}