package ru.sbx.spend_sense.presentation.root

import ru.sbx.spend_sense.presentation.base.BaseViewModel
import ru.sbx.spend_sense.presentation.root.RootContract.State

class RootViewModel : BaseViewModel<State, Nothing>() {
    override fun initialState() = State.NONE
}