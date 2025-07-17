package ru.sbx.spend_sense.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.sbx.spend_sense.db.AppDb
import java.util.prefs.Preferences

actual val platformModule: Module = module {
    single<Settings> { PreferencesSettings(Preferences.userRoot()) }
    single<SqlDriver> {
        //Todo: need to check if db had been created or not.
//        val driver = JdbcSqliteDriver("jdbc:sqlite:AppDb.db")
        //Each app running will create new inmemory DB
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        AppDb.Schema.create(driver)
        driver
    }
}