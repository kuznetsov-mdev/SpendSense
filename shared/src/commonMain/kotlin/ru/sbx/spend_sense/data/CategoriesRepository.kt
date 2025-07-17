package ru.sbx.spend_sense.data

import ru.sbx.spend_sense.data.model.dao.CategoryDao
import ru.sbx.spend_sense.presentation.categories.model.Category

class CategoriesRepository(
    private val dao: CategoryDao
) {
    fun getAllFlow() = dao.getAllFlow()

    suspend fun createCategory(category: Category) = dao.insert(category)
}