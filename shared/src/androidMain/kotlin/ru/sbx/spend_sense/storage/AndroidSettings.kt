package ru.sbx.spend_sense.storage

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import ru.sbx.spend_sense.App

actual class AppSettings {
    actual val settings: Settings
        get() = SharedPreferencesSettings(
            App.instance.getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        )
}