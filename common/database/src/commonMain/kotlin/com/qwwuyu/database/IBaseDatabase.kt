package com.qwwuyu.database

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.maybe.Maybe
import com.badoo.reaktive.observable.Observable

interface IBaseDatabase {
    fun observeAll(): Observable<List<KV>>

    fun select(k: String): Maybe<KV>

    fun setValue(k: String, v: String): Completable

    fun delete(k: String): Completable

    fun clear(): Completable
}
