package ru.sbx.spend_sense.extentions

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun appLog(message: String) {
    Napier.d("SpendSense: 🚀 $message")
}

fun initLogs() = Napier.base(DebugAntilog())