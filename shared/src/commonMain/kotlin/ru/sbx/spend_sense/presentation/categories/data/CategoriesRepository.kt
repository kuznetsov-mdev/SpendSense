package ru.sbx.spend_sense.presentation.categories.data

import kotlinx.coroutines.flow.flow
import ru.sbx.spend_sense.presentation.categories.model.Category

class CategoriesRepository {
    fun getAllFlow() = flow {
        emit(
            List(20) { index ->
                Category.NONE.copy(
                    id = index.toString(),
                    title = "category $index"
                )
            }
        )
    }

    suspend fun createCategory(category: Category) = Unit
}