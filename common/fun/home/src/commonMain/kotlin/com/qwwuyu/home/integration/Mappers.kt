package com.qwwuyu.home.integration

import com.qwwuyu.home.MHome.Model
import com.qwwuyu.home.store.HomeStore.State

internal val stateToModel: (State) -> Model = {
    Model(items = it.items)
}