package ru.sbx.spend_sense.presentation.root

import ru.sbx.spend_sense.presentation.base.BaseViewState
import ru.sbx.spend_sense.presentation.common.ui.AppPrefs

class RootContract {

    data class State(
        val isDarkTheme: Boolean,
        val isFirstDayMonday: Boolean
    ) : BaseViewState {

        val appPrefs: AppPrefs
            get() = AppPrefs(firstDayIsMonday = isFirstDayMonday)

        companion object {
            val NONE = State(
                isDarkTheme = true,
                isFirstDayMonday = true
            )
        }
    }
}