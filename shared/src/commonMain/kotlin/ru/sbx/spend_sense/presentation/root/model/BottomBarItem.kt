package ru.sbx.spend_sense.presentation.root.model

import ru.sbx.spend_sense.MR

data class BottomBarItem(
    var title: String,
    var appTab: AppTab
) {
    companion object {
        fun getItems() = listOf(
            BottomBarItem(MR.string.events, AppTab.Events),
            BottomBarItem(MR.string.categories, AppTab.Categories),
            BottomBarItem(MR.string.settings, AppTab.Settings)
        )
    }
}