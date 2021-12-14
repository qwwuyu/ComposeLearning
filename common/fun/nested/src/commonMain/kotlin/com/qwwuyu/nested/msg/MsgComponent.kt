package com.qwwuyu.nested.msg

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.qwwuyu.base.ext.asValue
import com.qwwuyu.nested.msg.MMsg.Model
import com.qwwuyu.nested.msg.MsgStore.State

class MsgComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory
) : MMsg, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        MsgStoreProvider(storeFactory = storeFactory).provide()
    }

    override val models: Value<Model> = store.asValue().map(stateToModel)

    override fun onClicked() {
        store.accept(MsgStore.Intent.Clicked)
    }

    override fun onTextChanged(text: String) {
        store.accept(MsgStore.Intent.TextChanged(text))
    }
}

internal val stateToModel: (State) -> Model = {
    Model(text = it.text)
}