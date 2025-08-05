package ru.sbx.spend_sense.presentation.auth.register

import ru.sbx.spend_sense.presentation.base.BaseEvent
import ru.sbx.spend_sense.presentation.base.BaseViewState

class RegisterContract {
    data class State(
        val email: String,
        val password: String,
        val confirmPassword: String
    ) : BaseViewState {

        val sendIsActive: Boolean
            get() = password.isNotBlank() && confirmPassword.isNotBlank() && password == confirmPassword

        companion object {
            val NONE = State(
                email = "",
                password = "",
                confirmPassword = ""
            )
        }
    }

    sealed interface Event : BaseEvent {
        data object Success : Event
        data class Error(val message: String) : Event
    }
}