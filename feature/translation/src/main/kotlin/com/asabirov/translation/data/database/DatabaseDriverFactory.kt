package com.asabirov.translation.data.database

import android.content.Context
import com.asabirov.translation.database.TranslateDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

class DatabaseDriverFactory(
    private val context: Context
) {
     fun create(): SqlDriver {
        return AndroidSqliteDriver(
            TranslateDatabase.Schema, context, "translate.db"
        )
    }
}