package ru.sbx.spend_sense.data

import ru.sbx.spend_sense.data.storage.dao.CategoryDao
import ru.sbx.spend_sense.presentation.categories.model.Category

class CategoriesRepository(
    private val dao: CategoryDao
) {
    fun getAllFlow() = dao.getAllFlow()

    suspend fun getAll() = dao.getAll()

    suspend fun insertAll(categories: List<Category>) = dao.insertAll(categories)

    suspend fun createCategory(category: Category) = dao.insert(category)
}