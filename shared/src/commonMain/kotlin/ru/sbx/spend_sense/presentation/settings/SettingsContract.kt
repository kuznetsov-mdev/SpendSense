package ru.sbx.spend_sense.presentation.settings

import ru.sbx.spend_sense.presentation.base.BaseViewState

class SettingsContract {
    data class State(
        val deviceInfo: String,
        val isDarkTheme: Boolean
    ) : BaseViewState {
        companion object {
            val NONE = State(
                deviceInfo = "",
                isDarkTheme = false
            )
        }
    }
}