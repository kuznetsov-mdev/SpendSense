package ru.sbx.spend_sense.presentation.root.model

sealed interface AppTab {
    data object Events : AppTab
    data object Categories : AppTab
    data object Settings : AppTab
}