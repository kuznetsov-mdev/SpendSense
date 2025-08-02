package ru.sbx.spend_sense.data.network.categories

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryApi(
    @SerialName("idLocal")
    val id: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("colorHex")
    val colorHex: String?,
    @SerialName("createdAtLocal")
    val createdAt: LocalDateTime?,
    @SerialName("updatedAtLocal")
    val updatedAt: LocalDateTime?,
)