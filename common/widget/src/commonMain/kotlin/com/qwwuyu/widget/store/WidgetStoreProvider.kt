package com.qwwuyu.widget.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor
import com.qwwuyu.widget.store.WidgetStore.Intent
import com.qwwuyu.widget.store.WidgetStore.State

internal class WidgetStoreProvider(
    private val storeFactory: StoreFactory,
    private val type: String
) {
    fun provide(): WidgetStore = object : WidgetStore, Store<Intent, State, Nothing> by storeFactory.create(
        name = "WidgetStore$type",
        initialState = State(),
        bootstrapper = SimpleBootstrapper(Unit),
        executorFactory = ::ExecutorImpl,
        reducer = ReducerImpl
    ) {}

    private sealed class Result {
        data class ItemsLoaded(val type: String) : Result()
    }

    private inner class ExecutorImpl : ReaktiveExecutor<Intent, Unit, State, Result, Nothing>() {
        override fun executeAction(action: Unit, getState: () -> State) {
            dispatch(Result.ItemsLoaded(type))
        }

        override fun executeIntent(intent: Intent, getState: () -> State): Unit = Unit
    }

    private object ReducerImpl : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                is Result.ItemsLoaded -> copy(type = result.type)
            }
    }
}
