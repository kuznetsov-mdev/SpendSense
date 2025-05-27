package ru.sbx.spend_sense.presentation.settings

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.sbx.spend_sense.platform.DeviceInfo
import ru.sbx.spend_sense.presentation.base.BaseViewModel
import ru.sbx.spend_sense.presentation.settings.SettingsContract.State
import ru.sbx.spend_sense.storage.SettingsManager

class SettingsViewModel(
    deviceInfo: DeviceInfo,
    private val settingsManager: SettingsManager
) : BaseViewModel<State, Nothing>() {

    init {
        settingsManager.isDarkThemFlow.onEach {
            updateState { copy(isDarkTheme = it) }
        }.launchIn(viewModelScope)

        updateState {
            copy(info = deviceInfo.getSummary())
        }
    }

    override fun initialState() = State.NONE

    fun switchTheme(isDark: Boolean) {
        settingsManager.isDarkTheme = isDark
    }
}