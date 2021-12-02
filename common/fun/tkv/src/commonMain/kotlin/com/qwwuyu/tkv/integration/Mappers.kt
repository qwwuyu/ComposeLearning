package com.qwwuyu.tkv.integration

import com.qwwuyu.tkv.MKV.Model
import com.qwwuyu.tkv.store.KVStore.State

internal val stateToModel: (State) -> Model = {
    Model(items = it.items, addItem = it.addItem, editItem = it.editItem)
}