package com.qwwuyu.widget.integration

import com.qwwuyu.widget.MWidget.Model
import com.qwwuyu.widget.store.WidgetStore.State

internal val stateToModel: (State) -> Model = {
    Model(type = it.type)
}