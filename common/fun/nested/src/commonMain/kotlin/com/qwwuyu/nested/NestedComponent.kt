package com.qwwuyu.nested

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.Router
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import com.qwwuyu.base.ext.TypeConfiguration
import com.qwwuyu.base.ext.asValue
import com.qwwuyu.base.ext.frontOrPush
import com.qwwuyu.nested.MNested.*
import com.qwwuyu.nested.home.HomeComponent
import com.qwwuyu.nested.me.MeComponent
import com.qwwuyu.nested.msg.MsgComponent

class NestedComponent(
    componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val output: Consumer<Output>,
) : MNested, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        NestedStoreProvider(storeFactory = storeFactory).provide()
    }

    private val router: Router<ChildConfiguration, Child> =
        router(
            initialConfiguration = ChildConfiguration(0),
            handleBackButton = false,
            childFactory = ::resolveChild
        )

    override val routerState: Value<RouterState<*, Child>> = router.state

    override val models: Value<Model> = store.asValue().map(stateToModel)

    private fun resolveChild(configuration: ChildConfiguration, componentContext: ComponentContext): Child =
        when (configuration.type) {
            1 -> Child.Msg(MsgComponent(componentContext, storeFactory))
            2 -> Child.Me(MeComponent(componentContext, storeFactory))
            else -> Child.Home(HomeComponent(componentContext, storeFactory))
        }

    override fun finish() {
        output(Output.Finished)
    }

    override fun onClicked(type: Int) {
        router.frontOrPush(ChildConfiguration(type))
    }

    @Parcelize
    private data class ChildConfiguration(override val type: Int) : TypeConfiguration<Int>
}

internal val stateToModel: (NestedStore.State) -> Model = {
    Model(type = it.type)
}