package com.qwwuyu.router.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.pop
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.qwwuyu.base.ext.Consumer
import com.qwwuyu.database.IBaseDatabase
import com.qwwuyu.home.MHome
import com.qwwuyu.home.integration.HomeComponent
import com.qwwuyu.nested.MNested
import com.qwwuyu.nested.NestedComponent
import com.qwwuyu.router.MRouter
import com.qwwuyu.router.MRouter.Child
import com.qwwuyu.tkv.MKV
import com.qwwuyu.tkv.integration.KVComponent
import com.qwwuyu.widget.MWidget
import com.qwwuyu.widget.integration.WidgetComponent

class RouterComponent internal constructor(
    componentContext: ComponentContext,
    private val home: (ComponentContext, Consumer<MHome.Output>) -> MHome,
    private val widget: (ComponentContext, type: String, Consumer<MWidget.Output>) -> MWidget,
    private val kv: (ComponentContext, Consumer<MKV.Output>) -> MKV,
    private val nested: (ComponentContext, Consumer<MNested.Output>) -> MNested,
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
        widget = { childContext, type, output ->
            WidgetComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                type = type,
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
        },
        nested = { childContext, output ->
            NestedComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
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
            is Configuration.WIDGET -> Child.Widget(
                widget(componentContext, configuration.type, Consumer(::onWidgetOutput))
            )
            is Configuration.Nested -> Child.Nested(nested(componentContext, Consumer(::onNestedOutput)))
        }

    private fun onMHomeOutput(output: MHome.Output): Unit =
        when (output) {
            is MHome.Output.Widget -> router.push(Configuration.WIDGET(output.type))
            is MHome.Output.Nested -> router.push(Configuration.Nested)
        }

    private fun onMKVOutput(output: MKV.Output): Unit =
        when (output) {
            is MKV.Output.Finished -> router.pop()
        }

    private fun onWidgetOutput(output: MWidget.Output): Unit =
        when (output) {
            is MWidget.Output.Finished -> router.pop()
            is MWidget.Output.TKV -> router.push(Configuration.KV)
            is MWidget.Output.Selected -> router.push(Configuration.WIDGET(output.type))
        }

    private fun onNestedOutput(output: MNested.Output): Unit =
        when (output) {
            is MNested.Output.Finished -> router.pop()
        }

    private sealed class Configuration : Parcelable {
        @Parcelize
        object HOME : Configuration()

        @Parcelize
        object KV : Configuration()

        @Parcelize
        data class WIDGET(val type: String) : Configuration()

        @Parcelize
        object Nested : Configuration()
    }
}
