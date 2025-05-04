package ru.sbx.spend_sense.platform

import android.content.res.Resources
import android.os.Build
import androidx.annotation.RequiresApi
import kotlin.math.roundToInt

actual class DeviceInfo {
    private val displayMetrics = Resources.getSystem().displayMetrics

    actual val osName: String = "Android"
    actual val osVersion: String = "${Build.VERSION.SDK_INT}"

    @RequiresApi(Build.VERSION_CODES.DONUT)
    actual val model: String = "${Build.MANUFACTURER} ${Build.MODEL}"

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    actual val cpu: String = Build.SUPPORTED_ABIS.firstOrNull() ?: "Unknown cpu"
    actual val screenWidth: Int = displayMetrics.widthPixels
    actual val screenHeight: Int = displayMetrics.heightPixels
    actual val screenDestiny: Int = displayMetrics.density.roundToInt()

    actual fun getSummary(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            "osName: $osName\n" +
                    "osVersion: $osVersion\n" +
                    "model: $model\n" +
                    "cpu: $cpu\n" +
                    "screenWidth: $screenWidth\n" +
                    "screenHeight: $screenHeight\n" +
                    "screenDensity: $screenDestiny\n" +
                    ")"
        } else {
            "osName: $osName\n" +
                    "osVersion: $osVersion\n" +
                    "model: unknown\n" +
                    "cpu: unknown\n" +
                    "screenWidth: $screenWidth\n" +
                    "screenHeight: $screenHeight\n" +
                    "screenDensity: $screenDestiny\n" +
                    ")"
        }
    }


}