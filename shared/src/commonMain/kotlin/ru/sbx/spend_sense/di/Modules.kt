package ru.sbx.spend_sense.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.sbx.spend_sense.platform.DeviceInfo
import ru.sbx.spend_sense.storage.SettingsManager

expect val platformModule: Module

object CoreModule {
    val deviceInfo = module {
        single { DeviceInfo() }
    }
}

object StorageModule {
    val settings = module {
        single { SettingsManager(get()) }
    }
}