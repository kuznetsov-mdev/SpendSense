package ru.sbx.spend_sense.presentation.auth.register

import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.sbx.spend_sense.data.network.ApiErrorWrapper
import ru.sbx.spend_sense.data.network.AppApi
import ru.sbx.spend_sense.data.network.auth.AuthResponse
import ru.sbx.spend_sense.data.network.auth.register.RegisterRequest
import ru.sbx.spend_sense.data.storage.SettingsManager
import ru.sbx.spend_sense.extentions.appLog
import ru.sbx.spend_sense.presentation.base.BaseViewModel

class RegisterViewModel(
    private val api: AppApi,
    private val settings: SettingsManager
) : BaseViewModel<RegisterContract.State, RegisterContract.Event>() {

    fun changeEmail(email: String) = updateState { copy(email = email) }
    fun changePassword(pass: String) = updateState { copy(password = pass) }
    fun changeConfirmPassword(pass: String) = updateState { copy(confirmPassword = pass) }

    fun register() {
        val registerReq = RegisterRequest(
            email = state.value.email,
            password = state.value.password,
            username = state.value.email
        )
        viewModelScope.launch(
            CoroutineExceptionHandler { _, t ->
                appLog(t.stackTraceToString())
                pushEvent(RegisterContract.Event.Error(t.message.orEmpty()))
            }
        ) {
            val response = api.register(registerReq)
            if (response.status.isSuccess()) {
                val regResponse = response.body<AuthResponse>() //cast response body to generic type -> AuthResponse
                settings.token = regResponse.jwt.orEmpty()
                settings.email = state.value.email
                pushEvent(RegisterContract.Event.Success)
            } else {
                val error = response.body<ApiErrorWrapper>().error
                pushEvent(RegisterContract.Event.Error(error?.message ?: response.bodyAsText()))
            }
        }
    }

    override fun initialState(): RegisterContract.State = RegisterContract.State.NONE
}