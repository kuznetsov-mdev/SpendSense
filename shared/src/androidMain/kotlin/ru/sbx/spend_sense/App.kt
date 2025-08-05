package ru.sbx.spend_sense

import android.app.Application
import android.content.Context
import org.koin.dsl.module
import ru.sbx.spend_sense.di.initKoin
import ru.sbx.spend_sense.extentions.initLogs

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initLogs()
        initKoin(appModule = module {
            single<Context> { this@App.applicationContext }
        })
        instance = this
    }

    companion object {
        lateinit var instance: App
    }
}