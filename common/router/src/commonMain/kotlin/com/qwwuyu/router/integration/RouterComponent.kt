package com.qwwuyu.router.integration

import com.arkivanov.decompose.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.qwwuyu.base.utils.Consumer
import com.qwwuyu.database.IBaseDatabase
import com.qwwuyu.home.MHome
import com.qwwuyu.home.integration.HomeComponent
import com.qwwuyu.router.MRouter
import com.qwwuyu.router.MRouter.Child
import com.qwwuyu.tkv.MKV
import com.qwwuyu.tkv.integration.KVComponent

class RouterComponent internal constructor(
    componentContext: ComponentContext,
    private val home: (ComponentContext, Consumer<MHome.Output>) -> MHome,
    private val kv: (ComponentContext, Consumer<MKV.Output>) -> MKV
) : MRouter, ComponentContext by componentContext {

    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
        database: IBaseDatabase
    ) : this(
        componentContext = componentContext,
        home = { childContext, output ->
            HomeComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                database = database,
                output = output
            )
        },
        kv = { childContext, output ->
            KVComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                database = database,
                output = output
            )
        }
    )

    private val router = router<Configuration, Child>(
        initialConfiguration = Configuration.HOME,
        handleBackButton = true,
        childFactory = ::createChild
    )

    override val routerState: Value<RouterState<*, Child>> = router.state

    private fun createChild(configuration: Configuration, componentContext: ComponentContext): Child =
        when (configuration) {
            is Configuration.HOME -> Child.Home(home(componentContext, Consumer(::onMHomeOutput)))
            is Configuration.KV -> Child.KV(kv(componentContext, Consumer(::onMKVOutput)))
        }

    private fun onMHomeOutput(output: MHome.Output): Unit =
        when (output) {
            is MHome.Output.Selected -> router.push(Configuration.KV)
        }

    private fun onMKVOutput(output: MKV.Output): Unit =
        when (output) {
            is MKV.Output.Finished -> router.pop()
            is MKV.Output.Selected -> router.pop()
        }

    private sealed class Configuration : Parcelable {
        @Parcelize
        object HOME : Configuration()

        @Parcelize
        object KV : Configuration()
    }
}
