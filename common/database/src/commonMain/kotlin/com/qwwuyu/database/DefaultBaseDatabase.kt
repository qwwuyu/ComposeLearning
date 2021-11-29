package com.qwwuyu.database

import com.badoo.reaktive.base.setCancellable
import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.maybe.Maybe
import com.badoo.reaktive.observable.*
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.single.*
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.db.SqlDriver

class DefaultBaseDatabase(driver: Single<SqlDriver>) : IBaseDatabase {
    constructor() : this(singleOf(BaseDatabaseDriver()))

    private val queries: Single<BaseDatabaseQueries> =
        driver
            .map { BaseDatabase(it).baseDatabaseQueries }
            .asObservable()
            .replay()
            .autoConnect()
            .firstOrError()

    override fun observeAll(): Observable<List<KV>> =
        query(BaseDatabaseQueries::selectAll)
            .observe { it.executeAsList() }

    override fun select(k: String): Maybe<KV> =
        query { it.select(k = k) }
            .mapNotNull { it.executeAsOneOrNull() }

    override fun setValue(k: String, v: String): Completable = execute {
        val kv = it.select(k = k).executeAsOneOrNull()
        if (kv == null) {
            it.add(k = k, v = v)
        } else {
            it.setValue(k = k, v = v)
        }
    }

    override fun delete(k: String): Completable = execute { it.delete(k = k) }

    override fun clear(): Completable = execute { it.clear() }

    private fun <T : Any> query(query: (BaseDatabaseQueries) -> Query<T>): Single<Query<T>> =
        queries
            .observeOn(ioScheduler)
            .map(query)

    private fun execute(query: (BaseDatabaseQueries) -> Unit): Completable =
        queries
            .observeOn(ioScheduler)
            .doOnBeforeSuccess(query)
            .asCompletable()

    private fun <T : Any, R> Single<Query<T>>.observe(get: (Query<T>) -> R): Observable<R> =
        flatMapObservable { it.observed() }
            .observeOn(ioScheduler)
            .map(get)

    private fun <T : Any> Query<T>.observed(): Observable<Query<T>> =
        observable { emitter ->
            val listener = object : Query.Listener {
                override fun queryResultsChanged() {
                    emitter.onNext(this@observed)
                }
            }
            emitter.onNext(this@observed)
            addListener(listener)
            emitter.setCancellable { removeListener(listener) }
        }
}
