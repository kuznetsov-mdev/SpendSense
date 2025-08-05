package ru.sbx.spend_sense.presentation.settings

import io.ktor.client.call.body
import io.ktor.http.isSuccess
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.sbx.spend_sense.data.CategoriesRepository
import ru.sbx.spend_sense.data.EventsRepository
import ru.sbx.spend_sense.data.network.AppApi
import ru.sbx.spend_sense.data.network.categories.CategoryApi
import ru.sbx.spend_sense.data.network.events.SpendEventApi
import ru.sbx.spend_sense.data.storage.SettingsManager
import ru.sbx.spend_sense.extentions.appLog
import ru.sbx.spend_sense.platform.DeviceInfo
import ru.sbx.spend_sense.presentation.base.BaseViewModel
import ru.sbx.spend_sense.presentation.categories.model.toApi
import ru.sbx.spend_sense.presentation.categories.model.toEntity
import ru.sbx.spend_sense.presentation.events.model.toApi
import ru.sbx.spend_sense.presentation.events.model.toEntity

class SettingsViewModel(
    private val deviceInfo: DeviceInfo,
    private val settingsManager: SettingsManager,
    private val categoriesRepository: CategoriesRepository,
    private val eventsRepository: EventsRepository,
    private val api: AppApi
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
        syncCategories()
        syncEvens()
        pushEvent(SettingsContract.Event.DataSynced)
        updateState { copy(isLoading = false) }
    }

    fun logout() {
        settingsManager.email = ""
        settingsManager.token = ""
    }

    //*********************** region private ********************

    private suspend fun syncCategories() {
        val apiCategories = categoriesRepository.getAll().map { it.toApi() }
        val categoriesSyncResponse = api.syncCategories(apiCategories)
        if (categoriesSyncResponse.status.isSuccess()) {
            val remoteCategories = categoriesSyncResponse.body<List<CategoryApi>>()
            categoriesRepository.insertAll(remoteCategories.map(CategoryApi::toEntity))
        }
    }

    private suspend fun syncEvens() {
        val apiEvents = eventsRepository.getAll().map { it.toApi() }
        val eventsSyncResponse = api.syncEvents(apiEvents)
        if (eventsSyncResponse.status.isSuccess()) {
            val remoteEvents = eventsSyncResponse.body<List<SpendEventApi>>()
            eventsRepository.insertAll(remoteEvents.map { it.toEntity() })
        }
    }

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