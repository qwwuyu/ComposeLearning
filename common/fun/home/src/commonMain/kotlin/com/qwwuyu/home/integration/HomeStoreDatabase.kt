package com.qwwuyu.home.integration

import com.qwwuyu.database.IBaseDatabase
import com.qwwuyu.home.store.HomeStoreProvider

internal class HomeStoreDatabase(
    private val database: IBaseDatabase
) : HomeStoreProvider.Database
