package ru.sbx.spend_sense.presentation.settings

import ru.sbx.spend_sense.platform.DeviceInfo
import ru.sbx.spend_sense.presentation.base.BaseViewModel
import ru.sbx.spend_sense.presentation.settings.SettingsContract.State

class SettingsViewModel : BaseViewModel<State, Nothing>() {

    init {
        val deviceInfo = DeviceInfo()

        updateState {
            copy(deviceInfo = deviceInfo.getSummary())
        }
    }

    override fun initialState() = State.NONE
}