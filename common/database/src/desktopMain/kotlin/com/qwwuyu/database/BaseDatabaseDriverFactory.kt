package com.qwwuyu.database

import com.qwwuyu.base.dataDir
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import java.io.File

actual fun BaseDatabaseDriver(): SqlDriver {
    val databaseDir = File(dataDir(), "database")
    if (!databaseDir.exists()) databaseDir.mkdir()
    val databasePath = File(databaseDir, "BaseDatabase.db")
    val driver = JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.absolutePath}")
    BaseDatabase.Schema.create(driver)
    return driver
}