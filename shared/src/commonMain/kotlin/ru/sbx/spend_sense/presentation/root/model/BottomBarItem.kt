package ru.sbx.spend_sense.presentation.root.model

data class BottomBarItem(
    var title: String,
    var appTab: AppTab
) {
    companion object {
        fun getItems() = listOf(
            BottomBarItem("Events", AppTab.Events),
            BottomBarItem("Categories", AppTab.Categories),
            BottomBarItem("Settings", AppTab.Settings)
        )
    }
}