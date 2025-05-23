package ru.sbx.spend_sense.di

import com.russhwolf.settings.NSUserDefaultsSettings
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

actual val platformModule: Module = module {

    single {
        NSUserDefaults.standardUserDefaults()
    }
    single {
        NSUserDefaultsSettings(get())
    }
}

object IosKoin {
    fun initialize() = initKoin()
}