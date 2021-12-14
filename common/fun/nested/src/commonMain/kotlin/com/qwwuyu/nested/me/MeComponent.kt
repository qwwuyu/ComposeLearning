package com.qwwuyu.nested.me

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.qwwuyu.base.ext.asValue
import com.qwwuyu.nested.me.MMe.Model
import com.qwwuyu.nested.me.MeStore.Intent
import com.qwwuyu.nested.me.MeStore.State

class MeComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory
) : MMe, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        MeStoreProvider(storeFactory = storeFactory).provide()
    }

    override val models: Value<Model> = store.asValue().map(stateToModel)

    override fun onClicked() {
        store.accept(Intent.Clicked)
    }

    override fun onTextChanged(text: String) {
        store.accept(Intent.TextChanged(text))
    }
}

internal val stateToModel: (State) -> Model = {
    Model(text = it.text)
}