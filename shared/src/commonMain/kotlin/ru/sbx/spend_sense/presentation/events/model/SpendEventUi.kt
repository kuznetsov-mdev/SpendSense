package ru.sbx.spend_sense.presentation.events.model

import ru.sbx.spend_sense.presentation.categories.model.Category

data class SpendEventUi(
    val id: String,
    val category: Category,
    val title: String,
    val cost: Double
)
