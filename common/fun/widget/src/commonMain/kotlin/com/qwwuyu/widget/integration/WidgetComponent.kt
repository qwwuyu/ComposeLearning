package com.qwwuyu.widget.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import com.qwwuyu.base.ext.asValue
import com.qwwuyu.widget.MWidget
import com.qwwuyu.widget.MWidget.Model
import com.qwwuyu.widget.MWidget.Output
import com.qwwuyu.widget.store.WidgetStoreProvider

class WidgetComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    type: String,
    private val output: Consumer<Output>
) : MWidget, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        WidgetStoreProvider(
            storeFactory = storeFactory,
            type = type
        ).provide()
    }

    override val models: Value<Model> = store.asValue().map(stateToModel)

    override fun finish() {
        output(Output.Finished)
    }

    override fun onItemClicked(type: String) {
        output(Output.Selected(type = type))
    }

    override fun onTKV() {
        output(Output.TKV)
    }
}
