package com.qwwuyu.tkv.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import com.qwwuyu.base.utils.asValue
import com.qwwuyu.database.IBaseDatabase
import com.qwwuyu.tkv.KVItem
import com.qwwuyu.tkv.MKV
import com.qwwuyu.tkv.MKV.Model
import com.qwwuyu.tkv.MKV.Output
import com.qwwuyu.tkv.store.KVStore.Intent
import com.qwwuyu.tkv.store.KVStoreProvider

class KVComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    database: IBaseDatabase,
    private val output: Consumer<Output>
) : MKV, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        KVStoreProvider(
            storeFactory = storeFactory,
            database = KVStoreDatabase(database = database)
        ).provide()
    }

    override val models: Value<Model> = store.asValue().map(stateToModel)

    override fun finish() {
        output(Output.Finished)
    }

    override fun onItemDeleteClicked(key: String) {
        store.accept(Intent.DeleteItem(key = key))
    }

    override fun onAddClicked() {
        store.accept(Intent.AddClick)
    }

    override fun onItemClicked(item: KVItem) {
        store.accept(Intent.ItemClicked(item))
    }

    override fun onConfirm() {
        store.accept(Intent.Confirm)
    }

    override fun onClose() {
        store.accept(Intent.Close)
    }

    override fun onKeyTextChanged(text: String) {
        store.accept(Intent.KeyTextChanged(text))
    }

    override fun onValueTextChanged(text: String) {
        store.accept(Intent.ValueTextChanged(text))
    }
}
