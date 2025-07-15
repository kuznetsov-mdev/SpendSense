package ru.sbx.spend_sense.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sbx.spend_sense.db.AppDb

actual val platformModule = module {
    single { get<Context>().getSharedPreferences("appSettings", Context.MODE_PRIVATE) }
    single { SharedPreferencesSettings(get()) } bind Settings::class
    single<SqlDriver> { AndroidSqliteDriver(AppDb.Schema, get(), "AppDb") }
}