import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ru.sbx.spend_sense.di.initKoin
import ru.sbx.spend_sense.presentation.root.compose.RootScreen

fun main() {

    initKoin()

    application {
        val state = rememberWindowState().apply { size = DpSize(1200.dp, 1200.dp) }
        Window(
            onCloseRequest = {exitApplication()},
            state = state,
            title = "SpendSense"
        ) {
            RootScreen()
        }
    }
}
