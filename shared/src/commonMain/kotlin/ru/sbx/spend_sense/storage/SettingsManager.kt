package ru.sbx.spend_sense.storage

import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

expect class AppSettings constructor() {
    val settings: Settings
}

object SettingsManager {
    private const val THEME_KEY = "THEME_KEY"

    val appSettings: AppSettings = AppSettings()

    private val _isDarkThemeFlow = MutableStateFlow(isDarkTheme)
    val isDarkThemFlow = _isDarkThemeFlow.asStateFlow()

    var isDarkTheme: Boolean
        set(value) {
            _isDarkThemeFlow.update { value }
            appSettings.settings.putBoolean(THEME_KEY, value)
        }
        get() = appSettings.settings.getBoolean(THEME_KEY, true)

}