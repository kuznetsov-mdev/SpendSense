package ru.sbx.spend_sense.presentation.settings

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.sbx.spend_sense.data.storage.SettingsManager
import ru.sbx.spend_sense.extentions.appLog
import ru.sbx.spend_sense.platform.DeviceInfo
import ru.sbx.spend_sense.presentation.base.BaseViewModel

class SettingsViewModel(
    private val deviceInfo: DeviceInfo,
    private val settingsManager: SettingsManager
) : BaseViewModel<SettingsContract.State, SettingsContract.Event>() {
    init {
        bindToEmail()
        bindToTheme()
        bindToToken()
        initDeviceInfo()
    }

    fun switchTheme(isDark: Boolean) {
        settingsManager.isDarkTheme = isDark
    }

    fun sync() = viewModelScope.launch(
        CoroutineExceptionHandler { _, t ->
            appLog(t.stackTraceToString())
            pushEvent(SettingsContract.Event.Error(t.message.orEmpty()))
        }
    ) {
        updateState { copy(isLoading = true) }
        delay(3000)
        pushEvent(SettingsContract.Event.DataSynced)
        updateState { copy(isLoading = false) }
    }

    fun logout() {
        settingsManager.email = ""
        settingsManager.token = ""
    }

    //*********************** region private ********************

    private fun bindToEmail() {
        settingsManager.emailFlow.onEach { email ->
            updateState { copy(email = email) }
        }.launchIn(viewModelScope)
    }

    private fun bindToToken() {
        settingsManager.tokenFlow.onEach { token ->
            updateState { copy(isAuth = token.isNotBlank()) }
        }.launchIn(viewModelScope)
    }

    private fun initDeviceInfo() {
        updateState {
            copy(info = deviceInfo.getSummary())
        }
    }

    private fun bindToTheme() {
        settingsManager.isDarkThemFlow.onEach {
            updateState { copy(isDarkTheme = it) }
        }.launchIn(viewModelScope)
    }

    override fun initialState() = SettingsContract.State.NONE

    //endregion
}