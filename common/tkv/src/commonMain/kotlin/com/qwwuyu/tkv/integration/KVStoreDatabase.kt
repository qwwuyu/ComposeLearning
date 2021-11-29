package com.qwwuyu.tkv.integration

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.mapIterable
import com.qwwuyu.database.IBaseDatabase
import com.qwwuyu.tkv.KVItem
import com.qwwuyu.tkv.store.KVStoreProvider

internal class KVStoreDatabase(
    private val database: IBaseDatabase
) : KVStoreProvider.Database {
    override val updates: Observable<List<KVItem>>
        get() = database
            .observeAll()
            .mapIterable { KVItem(key = it.k, value = it.v) }

    override fun delete(key: String): Completable =
        database.delete(k = key)

    override fun add(key: String, value: String): Completable =
        database.setValue(k = key, v = value)
}
