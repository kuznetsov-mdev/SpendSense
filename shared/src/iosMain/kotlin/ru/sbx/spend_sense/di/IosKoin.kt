package ru.sbx.spend_sense.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults
import ru.sbx.spend_sense.db.AppDb

actual val platformModule: Module = module {
    single<SqlDriver> { NativeSqliteDriver(AppDb.Schema, "AppDb") }
}

object IosKoin {
    fun initialize(
        userDefaults: NSUserDefaults
    ) = initKoin(appModule = module {
        single {
            NSUserDefaultsSettings(userDefaults)
        } bind Settings::class
    })
}