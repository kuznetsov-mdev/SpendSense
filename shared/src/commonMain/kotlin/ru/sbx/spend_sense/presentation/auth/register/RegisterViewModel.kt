package ru.sbx.spend_sense.presentation.auth.register

import ru.sbx.spend_sense.presentation.base.BaseViewModel

class RegisterViewModel() : BaseViewModel<RegisterContract.State, RegisterContract.Event>() {

    fun changeEmail(email: String) = updateState { copy(email = email) }
    fun changePassword(pass: String) = updateState { copy(password = pass) }
    fun changeConfirmPassword(pass: String) = updateState { copy(confirmPassword = pass) }

    fun register() {
        //create request model and fill it by registration data
        //async call
        //go to server
        //show result
    }

    override fun initialState(): RegisterContract.State = RegisterContract.State.NONE
}