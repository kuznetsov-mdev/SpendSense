package ru.sbx.spend_sense.data.storage

import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SettingsManager(private val settings: Settings) {
    private val THEME_KEY = "THEME_KEY"

    private val _isDarkThemeFlow = MutableStateFlow(isDarkTheme)
    val isDarkThemFlow = _isDarkThemeFlow.asStateFlow()

    var isDarkTheme: Boolean
        set(value) {
            _isDarkThemeFlow.update { value }
            settings.putBoolean(THEME_KEY, value)
        }
        get() = settings.getBoolean(THEME_KEY, true)
}