package com.qwwuyu.home.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import com.qwwuyu.base.ext.asValue
import com.qwwuyu.database.IBaseDatabase
import com.qwwuyu.home.MHome
import com.qwwuyu.home.MHome.Model
import com.qwwuyu.home.MHome.Output
import com.qwwuyu.home.store.HomeStoreProvider

class HomeComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    database: IBaseDatabase,
    private val output: Consumer<Output>
) : MHome, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        HomeStoreProvider(
            storeFactory = storeFactory,
            database = HomeStoreDatabase(database = database)
        ).provide()
    }

    override val models: Value<Model> = store.asValue().map(stateToModel)

    override fun onWidget(type: String) {
        output(Output.Widget(type))
    }

    override fun onNested() {
        output(Output.Nested)
    }
}
