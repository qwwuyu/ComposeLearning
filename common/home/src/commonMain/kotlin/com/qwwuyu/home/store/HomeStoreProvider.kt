package com.qwwuyu.home.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor
import com.qwwuyu.home.HomeItem
import com.qwwuyu.home.store.HomeStore.Intent
import com.qwwuyu.home.store.HomeStore.State

internal class HomeStoreProvider(
    private val storeFactory: StoreFactory,
    private val database: Database
) {
    fun provide(): HomeStore = object : HomeStore, Store<Intent, State, Nothing> by storeFactory.create(
        name = "HomeStore",
        initialState = State(),
        bootstrapper = SimpleBootstrapper(Unit),
        executorFactory = ::ExecutorImpl,
        reducer = ReducerImpl
    ) {}

    private sealed class Result {
        data class ItemsLoaded(val items: List<HomeItem>) : Result()
    }

    private inner class ExecutorImpl : ReaktiveExecutor<Intent, Unit, State, Result, Nothing>() {
        override fun executeAction(action: Unit, getState: () -> State) {
        }

        override fun executeIntent(intent: Intent, getState: () -> State): Unit =
            when (intent) {
                is Intent.LoginItem -> Unit
            }
    }

    private object ReducerImpl : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                is Result.ItemsLoaded -> copy(items = result.items)
            }
    }

    interface Database {
    }
}
