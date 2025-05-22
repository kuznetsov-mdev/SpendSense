package ru.sbx.spend_sense

import android.app.Application
import ru.sbx.spend_sense.di.initKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        instance = this
    }

    companion object {
        lateinit var instance: App
    }
}