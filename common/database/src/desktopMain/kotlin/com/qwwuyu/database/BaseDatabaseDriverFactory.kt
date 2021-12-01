package com.qwwuyu.database

import com.qwwuyu.base.cacheDir
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import java.io.File

actual fun BaseDatabaseDriver(): SqlDriver {
    val databaseDir = File(cacheDir(), "database")
    if (!databaseDir.exists()) databaseDir.mkdir()
    val databasePath = File(databaseDir, "BaseDatabase.db")
    val driver = JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.absolutePath}")
    BaseDatabase.Schema.create(driver)
    return driver
}