package ru.sbx.spend_sense.presentation.events.creation

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import ru.sbx.spend_sense.extentions.now
import ru.sbx.spend_sense.platform.randomUUID
import ru.sbx.spend_sense.presentation.base.BaseViewModel
import ru.sbx.spend_sense.presentation.categories.model.Category
import ru.sbx.spend_sense.presentation.events.creation.model.CreationContract
import ru.sbx.spend_sense.presentation.events.model.SpendEvent

class EventCreationViewModel() : BaseViewModel<CreationContract.State, CreationContract.Event>() {

    override fun initialState() = CreationContract.State.NONE

    fun selectDate(date: LocalDate?) = updateState { copy(date = date ?: LocalDate.now()) }
    fun resetState() = updateState { CreationContract.State.NONE }
    fun changeTitle(title: String) = updateState { copy(title = title) }
    fun changeNote(note: String) = updateState { copy(note = note) }
    fun changeCost(cost: String) = updateState { copy(cost = cost.toDoubleOrNull() ?: this.cost) }
    fun selectCategory(category: Category) = updateState { copy(category = category) }

    fun finish() {
        val now = LocalDateTime.now()
        val spendEvent = with(state.value) {
            SpendEvent(
                id = randomUUID(),
                title = title,
                categoryId = category.id,
                date = date,
                cost = cost,
                createdAt = now,
                updatedAt = now,
                note = note
            )
        }
        resetState()
        pushEvent(CreationContract.Event.Finish(spendEvent))
    }

}