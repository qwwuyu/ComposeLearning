package com.qwwuyu.nested

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor
import com.qwwuyu.nested.NestedStore.Intent
import com.qwwuyu.nested.NestedStore.State

internal class NestedStoreProvider(
    private val storeFactory: StoreFactory
) {
    fun provide(): NestedStore = object : NestedStore, Store<Intent, State, Nothing> by storeFactory.create(
        name = "NestedStore",
        initialState = State(),
        bootstrapper = SimpleBootstrapper(Unit),
        executorFactory = ::ExecutorImpl,
        reducer = ReducerImpl
    ) {}

    private inner class ExecutorImpl : ReaktiveExecutor<Intent, Unit, State, Result, Nothing>() {
        override fun executeAction(action: Unit, getState: () -> State) {
        }

        override fun executeIntent(intent: Intent, getState: () -> State): Unit =
            when (intent) {
                is Intent.Clicked -> dispatch(Result.Clicked(intent.type))
            }
    }

    private sealed class Result {
        data class Clicked(val type: Int) : Result()
    }

    private object ReducerImpl : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                is Result.Clicked -> copy(type = result.type)
            }
    }
}
