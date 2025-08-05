package ru.sbx.spend_sense.presentation.auth.signin

import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.sbx.spend_sense.data.network.ApiErrorWrapper
import ru.sbx.spend_sense.data.network.AppApi
import ru.sbx.spend_sense.data.network.auth.AuthResponse
import ru.sbx.spend_sense.data.network.auth.signin.SignInRequest
import ru.sbx.spend_sense.data.storage.SettingsManager
import ru.sbx.spend_sense.presentation.base.BaseViewModel

class SignInViewModel(
    private val api: AppApi,
    private val settings: SettingsManager
) : BaseViewModel<SignInContract.State, SignInContract.Event>() {

    fun changeEmail(email: String) = updateState { copy(email = email) }
    fun changePassword(pass: String) = updateState { copy(password = pass) }
    fun login() {
        val signInReq = SignInRequest(
            identifier = state.value.email,
            password = state.value.password
        )
        viewModelScope.launch(
            CoroutineExceptionHandler { _, t ->
                pushEvent(SignInContract.Event.Error(t.message.orEmpty()))
            }
        ) {
            val response = api.signIn(signInReq)
            if (response.status.isSuccess()) {
                val signInResponse = response.body<AuthResponse>()
                settings.token = signInResponse.jwt.orEmpty()
                settings.email = state.value.email
                pushEvent(SignInContract.Event.Success)
            } else {
                val error = response.body<ApiErrorWrapper>().error
                pushEvent(SignInContract.Event.Error(error?.message ?: response.bodyAsText()))
            }
        }
    }

    override fun initialState(): SignInContract.State = SignInContract.State.NONE
}