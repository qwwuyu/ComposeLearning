package com.qwwuyu.compose.module.nested

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.qwwuyu.base.utils.TProvidableCompositionLocal
import com.qwwuyu.base.utils.WLog
import com.qwwuyu.compose.widget.MultiPane
import com.qwwuyu.nested.MNested
import com.qwwuyu.nested.home.MHome
import com.qwwuyu.nested.me.MMe
import com.qwwuyu.nested.msg.MMsg

@Composable
fun NestedContent(component: MNested) {
    val model by component.models.subscribeAsState()
    Column {
        TopAppBar(
            title = { Text("Nested Router") },
            navigationIcon = {
                IconButton(onClick = component::finish) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            },
        )
        Box(Modifier.weight(1F)) {
            CompositionLocalProvider(LocalNested provides component, LocalNestedModel provides model) {
                MultiPane {
                    first {
                        Column {
                            Button(onClick = {
                                ctrl(false)
                                component.onClicked(0)
                            }, modifier = Modifier.padding(8.dp)) { Text(text = "Home") }
                            Button(onClick = {
                                ctrl(false)
                                component.onClicked(1)
                            }, modifier = Modifier.padding(8.dp)) { Text(text = "Msg") }
                            Button(onClick = {
                                ctrl(false)
                                component.onClicked(2)
                            }, modifier = Modifier.padding(8.dp)) { Text(text = "Me") }
                        }
                    }
                    second {
                        Children(routerState = component.routerState/*, animation = crossfadeScale()*/) {
                            when (val child = it.instance) {
                                is MNested.Child.Home -> Home(child.component)
                                is MNested.Child.Msg -> Msg(child.component)
                                is MNested.Child.Me -> Me(child.component)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun rememberNested(): MNested = LocalNested.current
val LocalNested = TProvidableCompositionLocal<MNested>()

@Composable
fun rememberNestedModel(): MNested.Model = LocalNestedModel.current
val LocalNestedModel = TProvidableCompositionLocal<MNested.Model>()


@Composable
private fun Home(component: MHome) {
    val model by component.models.subscribeAsState()

    Column {
        OutlinedTextField(
            value = model.text,
            onValueChange = { component.onTextChanged(it) },
            modifier = Modifier,
            label = { Text("home") },
            singleLine = true
        )

        TList()
    }
}


@Composable
private fun Msg(component: MMsg) {
    val model by component.models.subscribeAsState()

    Column {
        OutlinedTextField(
            value = model.text,
            onValueChange = { component.onTextChanged(it) },
            modifier = Modifier,
            label = { Text("msg") },
            singleLine = true
        )
        TList()
    }
}

@Composable
private fun TList() {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        repeat(100) { Text("Text$it") }
    }
}

@Composable
private fun Me(component: MMe) {
    val count = remember {
        mutableStateOf(0L)
    }

    Button(onClick = { count.value++ }) {
        Text(text = "Test${count.value}")
    }

    if (count.value in 2..5) {
        LaunchedEffect(key1 = true, block = {
            WLog.i("LaunchedEffect${count.value}")
        })

        SideEffect {
            WLog.i("SideEffect${count.value}")
        }

        DisposableEffect(key1 = Unit, effect = {
            WLog.i("DisposableEffect${count.value}")
            onDispose {
                WLog.i("onDispose${count.value}")
            }
        })
    }
}