package ru.sbx.spend_sense.data.network.events

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpendEventWrapperApi(
    val data: SpendEventApi
)

@Serializable
data class SpendEventApi(
    @SerialName("idLocal")
    val id: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("cost")
    val cost: Double?,
    @SerialName("categoryId")
    val categoryId: String?,
    @SerialName("note")
    val note: String?,
    @Contextual
    @SerialName("createdAtLocal")
    val createdAt: LocalDateTime?,
    @Contextual
    @SerialName("updatedAtLocal")
    val updatedAt: LocalDateTime?,
    @Contextual
    @SerialName("date")
    val date: LocalDate?,
)