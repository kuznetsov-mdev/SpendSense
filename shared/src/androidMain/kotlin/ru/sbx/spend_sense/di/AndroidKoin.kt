package ru.sbx.spend_sense.di

import android.content.Context
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.dsl.module

actual val platformModule = module {
    single { get<Context>().getSharedPreferences("appSettings", Context.MODE_PRIVATE) }
    single { SharedPreferencesSettings(get()) }
}