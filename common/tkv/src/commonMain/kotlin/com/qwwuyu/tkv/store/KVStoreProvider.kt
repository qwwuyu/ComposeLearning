package com.qwwuyu.tkv.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor
import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.map
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.scheduler.mainScheduler
import com.qwwuyu.tkv.KVItem
import com.qwwuyu.tkv.store.KVStore.Intent
import com.qwwuyu.tkv.store.KVStore.State

internal class KVStoreProvider(
    private val storeFactory: StoreFactory,
    private val database: Database
) {
    fun provide(): KVStore = object : KVStore, Store<Intent, State, Nothing> by storeFactory.create(
        name = "KVStore",
        initialState = State(),
        bootstrapper = SimpleBootstrapper(Unit),
        executorFactory = ::ExecutorImpl,
        reducer = ReducerImpl
    ) {}

    private sealed class Result {
        data class ItemsLoaded(val items: List<KVItem>) : Result()
        data class ItemDeleted(val key: String) : Result()
        data class AddItemEnd(val key: String, val value: String) : Result()
    }

    private inner class ExecutorImpl : ReaktiveExecutor<Intent, Unit, State, Result, Nothing>() {
        override fun executeAction(action: Unit, getState: () -> State) {
            database
                .updates
                .observeOn(mainScheduler)
                .map(Result::ItemsLoaded)
                .subscribeScoped(onNext = ::dispatch)
        }

        override fun executeIntent(intent: Intent, getState: () -> State): Unit =
            when (intent) {
                is Intent.DeleteItem -> deleteItem(key = intent.key)
                is Intent.AddItem -> addItem(intent.key, intent.value)
            }

        private fun deleteItem(key: String) {
            dispatch(Result.ItemDeleted(key = key))
            database.delete(key = key).subscribeScoped()
        }

        private fun addItem(key: String, value: String) {
            dispatch(Result.AddItemEnd(key, value))
            database.add(key, value).subscribeScoped()
        }
    }

    private object ReducerImpl : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                is Result.ItemsLoaded -> copy(items = result.items)
                is Result.ItemDeleted -> copy(items = items.filterNot { it.key == result.key })
                is Result.AddItemEnd -> copy()
            }
    }

    interface Database {
        val updates: Observable<List<KVItem>>

        fun delete(key: String): Completable

        fun add(key: String, value: String): Completable
    }
}
