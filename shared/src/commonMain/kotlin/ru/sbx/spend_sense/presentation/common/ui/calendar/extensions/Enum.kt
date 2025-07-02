package ru.sbx.spend_sense.presentation.common.ui.calendar.extensions

inline fun <reified T : Enum<T>> Enum<T>.next(): T {
    val values = enumValues<T>()
    val currentIndex = values.indexOf(this)
    val nextIndex = (currentIndex + 1) % values.size
    return values[nextIndex]
}

inline fun <reified T : Enum<T>> Enum<T>.prev(): T {
    val values = enumValues<T>()
    val currentIndex = values.indexOf(this)
    val prevIndex = (currentIndex - 1 + values.size) % values.size
    return values[prevIndex]
}