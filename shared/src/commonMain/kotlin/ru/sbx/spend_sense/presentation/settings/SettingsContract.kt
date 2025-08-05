package ru.sbx.spend_sense.presentation.settings

import ru.sbx.spend_sense.presentation.base.BaseEvent
import ru.sbx.spend_sense.presentation.base.BaseViewState

class SettingsContract {
    data class State(
        val info: String,
        val isDarkTheme: Boolean,
        val isAuth: Boolean,
        val isLoading: Boolean,
        val email: String
    ) : BaseViewState {
        companion object {
            val NONE = State(
                info = "",
                isDarkTheme = false,
                isAuth = false,
                isLoading = false,
                email = ""
            )
        }
    }

    sealed interface Event : BaseEvent {
        data object DataSynced : Event
        data class Error(val message: String) : Event
    }
}