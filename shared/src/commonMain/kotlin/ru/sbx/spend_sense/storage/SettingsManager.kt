package ru.sbx.spend_sense.storage

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object SettingsManager {
    var isDarkTheme: Boolean = false
        set(value) {
            _isDarkThemeFlow.update { value }
            field = value
        }
    private val _isDarkThemeFlow = MutableStateFlow(isDarkTheme)
    val isDarkThemFlow = _isDarkThemeFlow.asStateFlow()
}