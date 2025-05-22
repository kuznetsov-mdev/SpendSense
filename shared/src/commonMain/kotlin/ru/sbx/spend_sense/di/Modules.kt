package ru.sbx.spend_sense.di

import org.koin.dsl.module
import ru.sbx.spend_sense.platform.DeviceInfo

object CoreModule {
    val deviceInfo = module {
        single { DeviceInfo() }
    }
}