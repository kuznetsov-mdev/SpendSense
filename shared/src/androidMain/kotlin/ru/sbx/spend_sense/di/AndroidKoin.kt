package ru.sbx.spend_sense.di

import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.dsl.module

actual val platformModule = module {
    single { SharedPreferencesSettings(get()) }
}