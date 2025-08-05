package ru.sbx.spend_sense.data.storage

import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SettingsManager(private val settings: Settings) {
    private val THEME_KEY = "THEME_KEY"
    private val TOKEN_KEY = "TOKEN_KEY"
    private val EMAIL_KEY = "EMAIL_KEY"
    val serverUrl = "http:// 192.168.0.149:1337"

    private val _isDarkThemeFlow = MutableStateFlow(isDarkTheme)
    val isDarkThemFlow = _isDarkThemeFlow.asStateFlow()

    private val _tokenFlow = MutableStateFlow(token)
    val tokenFlow = _tokenFlow.asStateFlow()

    private val _emailFlow = MutableStateFlow(email)
    val emailFlow = _emailFlow.asStateFlow()

    var isDarkTheme: Boolean
        set(value) {
            _isDarkThemeFlow.update { value }
            settings.putBoolean(THEME_KEY, value)
        }
        get() = settings.getBoolean(THEME_KEY, true)

    var token: String
        set(value) {
            settings.putString(TOKEN_KEY, value)
            _tokenFlow.update { value }
        }
        get() = settings.getString(TOKEN_KEY, "")

    var email: String
        set(value) {
            settings.putString(EMAIL_KEY, value)
            _emailFlow.update { value }
        }
        get() = settings.getString(EMAIL_KEY, "")
}