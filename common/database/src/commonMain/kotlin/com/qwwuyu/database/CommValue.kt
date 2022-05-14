package com.qwwuyu.database

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor

fun <T> getCommValue(
    default: T, name: String, componentContext: ComponentContext, storeFactory: StoreFactory
): DataStore<T> {
    val executor = object : ReaktiveExecutor<DataIntent<T>, Unit, DataModel<T>, DataIntent<T>, Nothing>() {
        override fun executeAction(action: Unit, getState: () -> DataModel<T>) {}
        override fun executeIntent(intent: DataIntent<T>, getState: () -> DataModel<T>): Unit = dispatch(intent)
    }
    val reducer = Reducer<DataModel<T>, DataIntent<T>> { result ->
        when (result) {
            is NewValue -> copy(data = result.data)
            is CopyValue -> copy(data = result.copy(data))
        }
    }
    return componentContext.instanceKeeper.getStore(name) {
        object : DataStore<T>, Store<DataIntent<T>, DataModel<T>, Nothing> by storeFactory.create(
            name = name,
            initialState = DataModel(default),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = { executor },
            reducer = reducer
        ) {}
    }
}

private typealias N<T> = NullModel<T>

fun <T> getNullValue(
    name: String, componentContext: ComponentContext, storeFactory: StoreFactory
): DataStore<N<T>> {
    val executor = object :
        ReaktiveExecutor<DataIntent<N<T>>, Unit, DataModel<N<T>>, DataIntent<N<T>>, Nothing>() {
        override fun executeAction(action: Unit, getState: () -> DataModel<N<T>>) {}
        override fun executeIntent(intent: DataIntent<N<T>>, getState: () -> DataModel<N<T>>): Unit =
            dispatch(intent)
    }
    val reducer = Reducer<DataModel<N<T>>, DataIntent<N<T>>> { result ->
        when (result) {
            is NewValue -> copy(data = result.data)
            is CopyValue -> copy(data = result.copy(data))
        }
    }
    return componentContext.instanceKeeper.getStore(name) {
        object : DataStore<N<T>>, Store<DataIntent<N<T>>, DataModel<N<T>>, Nothing> by storeFactory.create(
            name = name,
            initialState = DataModel(NullModel(null)),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = { executor },
            reducer = reducer
        ) {}
    }
}

data class DataModel<T>(val data: T)
data class NullModel<N>(val data: N?)

sealed class DataIntent<T>
data class NewValue<T>(val data: T) : DataIntent<T>()
data class CopyValue<T>(val copy: (T) -> T) : DataIntent<T>()

interface DataStore<T> : Store<DataIntent<T>, DataModel<T>, Nothing>
