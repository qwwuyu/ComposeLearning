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
                is Intent.AddClick -> dispatch(Result.AddClick)
                is Intent.ItemClicked -> dispatch(Result.ItemClicked(intent.item))
                is Intent.Confirm -> confirm(getState())
                is Intent.Close -> dispatch(Result.Close)
                is Intent.KeyTextChanged -> dispatch(Result.KeyTextChanged(intent.text))
                is Intent.ValueTextChanged -> dispatch(Result.ValueTextChanged(intent.text))
            }

        private fun deleteItem(key: String) {
            dispatch(Result.ItemDeleted(key = key))
            database.delete(key = key).subscribeScoped()
        }

        private fun confirm(state: State) {
            if (state.addItem.show) {
                dispatch(Result.Confirm)
                database.setValue(state.addItem.key, state.addItem.value).subscribeScoped()
            } else if (state.editItem.show) {
                dispatch(Result.Confirm)
                database.setValue(state.editItem.key, state.editItem.value).subscribeScoped()
            }
        }
    }

    private sealed class Result {
        data class ItemsLoaded(val items: List<KVItem>) : Result()
        data class ItemDeleted(val key: String) : Result()
        object AddClick : Result()
        data class ItemClicked(val item: KVItem) : Result()
        data class KeyTextChanged(val text: String) : Result()
        data class ValueTextChanged(val text: String) : Result()
        object Confirm : Result()
        object Close : Result()
    }

    private object ReducerImpl : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                is Result.ItemsLoaded -> copy(items = result.items)
                is Result.ItemDeleted -> copy(items = items.filterNot { it.key == result.key })
                is Result.AddClick -> {
                    if (!editItem.show) copy(addItem = addItem.copy(show = true))
                    else copy()
                }
                is Result.ItemClicked -> {
                    if (!addItem.show)
                        copy(editItem = editItem.copy(show = true, key = result.item.key, value = result.item.value))
                    else copy()
                }
                is Result.KeyTextChanged -> {
                    if (addItem.show) copy(addItem = addItem.copy(key = result.text))
                    else if (editItem.show) copy(editItem = editItem.copy(key = result.text))
                    else copy()
                }
                is Result.ValueTextChanged -> {
                    if (addItem.show) copy(addItem = addItem.copy(value = result.text))
                    else if (editItem.show) copy(editItem = editItem.copy(value = result.text))
                    else copy()
                }
                is Result.Confirm -> {
                    if (addItem.show) copy(addItem = addItem.copy(show = false, key = "", value = ""))
                    else if (editItem.show) copy(editItem = editItem.copy(show = false, key = "", value = ""))
                    else copy()
                }
                is Result.Close -> {
                    if (addItem.show) copy(addItem = addItem.copy(show = false))
                    else if (editItem.show) copy(editItem = editItem.copy(show = false))
                    else copy()
                }
            }
    }

    interface Database {
        val updates: Observable<List<KVItem>>

        fun delete(key: String): Completable

        fun setValue(key: String, value: String): Completable
    }
}
