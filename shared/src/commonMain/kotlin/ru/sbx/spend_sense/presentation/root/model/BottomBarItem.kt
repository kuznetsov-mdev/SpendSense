package ru.sbx.spend_sense.presentation.root.model

import io.github.skeptick.libres.images.Image
import ru.sbx.spend_sense.MR

data class BottomBarItem(
    var title: String,
    var appTab: AppTab,
    var icon: Image
) {
    companion object {
        fun getItems() = listOf(
            BottomBarItem(MR.string.events, AppTab.Events, MR.image.ic_calendar),
            BottomBarItem(MR.string.categories, AppTab.Categories, MR.image.ic_categories),
            BottomBarItem(MR.string.settings, AppTab.Settings, MR.image.ic_settings)
        )
    }
}