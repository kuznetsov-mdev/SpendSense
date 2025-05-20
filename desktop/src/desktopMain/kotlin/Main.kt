import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ru.sbx.spend_sense.SayHelloFromCommon

fun main() {

    application {
        val state = rememberWindowState().apply { size = DpSize(1200.dp, 1200.dp) }
        Window(
            onCloseRequest = {exitApplication()},
            state = state,
            title = "SpendSense"
        ) {
                SayHelloFromCommon()
        }
    }
}
