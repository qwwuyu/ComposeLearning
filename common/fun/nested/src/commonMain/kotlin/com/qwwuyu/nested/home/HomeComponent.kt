package com.qwwuyu.nested.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.qwwuyu.base.ext.asValue
import com.qwwuyu.nested.home.HomeStore.State
import com.qwwuyu.nested.home.MHome.Model

class HomeComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory
) : MHome, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        HomeStoreProvider(storeFactory = storeFactory).provide()
    }

    override val models: Value<Model> = store.asValue().map(stateToModel)

    override fun onClicked() {
        store.accept(HomeStore.Intent.Clicked)
    }

    override fun onTextChanged(text: String) {
        store.accept(HomeStore.Intent.TextChanged(text))
    }
}

internal val stateToModel: (State) -> Model = {
    Model(text = it.text)
}