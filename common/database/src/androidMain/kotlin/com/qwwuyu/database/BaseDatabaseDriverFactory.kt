package com.qwwuyu.database

import com.qwwuyu.base.LibApplication
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual fun BaseDatabaseDriver(): SqlDriver =
    AndroidSqliteDriver(
        schema = BaseDatabase.Schema,
        context = LibApplication.context,
        name = "BaseDatabase.db"
    )
