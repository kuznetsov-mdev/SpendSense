package ru.sbx.spend_sense.di

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

actual val platformModule: Module = module {
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