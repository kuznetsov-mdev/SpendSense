package ru.sbx.spend_sense.presentation.categories.creation.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import ru.sbx.spend_sense.MR
import ru.sbx.spend_sense.presentation.categories.creation.CreateCategoryData
import ru.sbx.spend_sense.presentation.common.ui.atoms.AppButton
import ru.sbx.spend_sense.presentation.common.ui.atoms.AppTextField
import ru.sbx.spend_sense.presentation.common.ui.atoms.BottomModalContainer

@Composable
fun CategoryCreationView(
    createListener: (CreateCategoryData) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }
    var rColor by remember { mutableFloatStateOf(0.5f) }
    var gColor by remember { mutableFloatStateOf(0.5f) }
    var bColor by remember { mutableFloatStateOf(0.5f) }

    BottomModalContainer {
        AppTextField(
            value = title,
            placeholder = MR.string.title_category_placeholder,
            modifier = Modifier.fillMaxWidth()
        ) { title = it }

        Spacer(modifier = Modifier.height(16.dp))

        AppTextField(
            value = subtitle,
            placeholder = MR.string.subtitle_category_placeholder,
            modifier = Modifier.fillMaxWidth()
        ) { subtitle = it }

        ColorBox(rColor, gColor, bColor) {
            ColorSlider(Color.Red, rColor) { rColor = it }
            ColorSlider(Color.Green, gColor) { gColor = it }
            ColorSlider(Color.Blue, bColor) { bColor = it }
        }

        Spacer(modifier = Modifier.height(16.dp))

        AppButton(MR.string.save) {
            createListener(
                CreateCategoryData(title, subtitle, Color(rColor, gColor, bColor).toArgb().toString(16))
            )
        }
    }
}