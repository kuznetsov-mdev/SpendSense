package ru.sbx.spend_sense.presentation.categories.list.compose

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import ru.sbx.spend_sense.data.CategoriesRepository
import ru.sbx.spend_sense.extentions.now
import ru.sbx.spend_sense.presentation.base.BaseViewModel
import ru.sbx.spend_sense.presentation.categories.creation.CreateCategoryData
import ru.sbx.spend_sense.presentation.categories.creation.toCategory
import ru.sbx.spend_sense.presentation.categories.model.CategoryContract

class CategoriesViewModel(
    private val repository: CategoriesRepository
) : BaseViewModel<CategoryContract.State, Nothing>() {

    override fun initialState() = CategoryContract.State.NONE

    init {
        activate()
    }

    fun createCategory(categoryData: CreateCategoryData) {
        viewModelScope.launch {
            repository.createCategory(categoryData.toCategory(LocalDateTime.now()))
        }
    }


    private fun activate() {
        repository.getAllFlow().onEach { categories ->
            updateState { copy(categories = categories) }
        }.launchIn(viewModelScope)
    }

}