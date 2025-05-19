package ru.sbx.spend_sense.presentation.categories

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.sbx.spend_sense.MR

@Composable
fun BoxScope.CategoriesScreen() {
    Text(MR.string.categories, modifier = Modifier.align(Alignment.Center))
}