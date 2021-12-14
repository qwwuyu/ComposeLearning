package com.qwwuyu.nested.home

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor
import com.qwwuyu.nested.home.HomeStore.Intent
import com.qwwuyu.nested.home.HomeStore.State

internal class HomeStoreProvider(
    private val storeFactory: StoreFactory
) {
    fun provide(): HomeStore = object : HomeStore, Store<Intent, State, Nothing> by storeFactory.create(
        name = "HomeStoreTT",
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
                is Intent.Clicked -> Unit
                is Intent.TextChanged -> dispatch(Result.TextChanged(intent.text))
            }
    }

    private sealed class Result {
        data class TextChanged(val text: String) : Result()
    }

    private object ReducerImpl : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                is Result.TextChanged -> copy(text = result.text)
            }
    }
}
