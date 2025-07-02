package ru.sbx.spend_sense.presentation.categories.model

import ru.sbx.spend_sense.presentation.base.BaseViewState

class CategoryContract {
    data class State(
        val categories: List<Category>
    ) : BaseViewState {
        companion object {
            val NONE = State(emptyList())
        }
    }
}