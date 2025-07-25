package ru.sbx.spend_sense.data.model.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import db.categories.CategoryDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.sbx.spend_sense.db.AppDb
import ru.sbx.spend_sense.presentation.categories.model.Category
import ru.sbx.spend_sense.presentation.categories.model.toDb
import ru.sbx.spend_sense.presentation.categories.model.toEntity
import kotlin.coroutines.CoroutineContext

class CategoryDao(
    private val db: AppDb,
    private val coroutineContext: CoroutineContext
) {
    private val categoriesQueries = db.categoryDbQueries

    fun getAll(): List<Category> =
        categoriesQueries
            .getAll()
            .executeAsList()
            .map { it.toEntity() }

    fun getAllFlow(): Flow<List<Category>> =
        categoriesQueries
            .getAll()
            .asFlow()
            .mapToList(coroutineContext)
            .map { categories -> categories.map(CategoryDb::toEntity) }

    suspend fun insert(category: Category) = categoriesQueries.insert(category.toDb())

    suspend fun insertAll(categories: List<Category>) =
        categoriesQueries.transaction {
            categories.forEach { categoriesQueries.insert(it.toDb()) }
        }

    suspend fun delete(id: String) = categoriesQueries.delete(id)
}
