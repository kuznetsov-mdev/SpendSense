package ru.sbx.spend_sense.presentation.root

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.sbx.spend_sense.data.storage.SettingsManager
import ru.sbx.spend_sense.presentation.base.BaseViewModel
import ru.sbx.spend_sense.presentation.root.model.AppTab
import ru.sbx.spend_sense.presentation.root.model.RootContract.State

class RootViewModel(
    settingsManager: SettingsManager
) : BaseViewModel<State, Nothing>() {
    override fun initialState() = State.NONE

    init {
        settingsManager.isDarkThemFlow.onEach { isDark ->
            updateState { copy(isDarkTheme = isDark) }
        }.launchIn(viewModelScope)
    }

    fun onTabClicked(tab: AppTab) = updateState { copy(selectedTab = tab) }
}