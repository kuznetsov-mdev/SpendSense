package ru.sbx.spend_sense.presentation.root

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.sbx.spend_sense.presentation.base.BaseViewModel
import ru.sbx.spend_sense.presentation.root.RootContract.State
import ru.sbx.spend_sense.storage.SettingsManager

class RootViewModel : BaseViewModel<State, Nothing>() {
    override fun initialState() = State.NONE

    init {
        SettingsManager.isDarkThemFlow.onEach { isDark ->
            updateState { copy(isDarkTheme = isDark) }
        }.launchIn(viewModelScope)
    }
}