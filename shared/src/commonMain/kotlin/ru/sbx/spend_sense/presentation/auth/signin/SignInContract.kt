package ru.sbx.spend_sense.presentation.auth.signin

import ru.sbx.spend_sense.presentation.base.BaseEvent
import ru.sbx.spend_sense.presentation.base.BaseViewState

class SignInContract {

    data class State(
        val email: String,
        val password: String
    ) : BaseViewState {
        companion object {
            val NONE = State(
                email = "",
                password = ""
            )
        }
    }

    sealed interface Event : BaseEvent {
        data object Success : Event
        data class Error(val message: String) : Event
    }
}