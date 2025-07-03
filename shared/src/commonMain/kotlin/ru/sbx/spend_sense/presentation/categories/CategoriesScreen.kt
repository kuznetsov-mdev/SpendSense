package ru.sbx.spend_sense.presentation.categories

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch
import ru.sbx.spend_sense.presentation.categories.creation.compose.CategoryCreationView
import ru.sbx.spend_sense.presentation.categories.list.compose.CategoriesListView
import ru.sbx.spend_sense.presentation.categories.list.compose.CategoriesViewModel
import ru.sbx.spend_sense.presentation.common.ui.atoms.FAB
import ru.sbx.spend_sense.presentation.common.ui.atoms.RootBox

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BoxScope.CategoriesScreen(
    viewModel: CategoriesViewModel
) {
    val sheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden, skipHalfExpanded = true
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = {
            CategoryCreationView(
                isExpanded = sheetState.currentValue == ModalBottomSheetValue.Expanded
            ) { data ->
                scope.launch { sheetState.hide() }
                viewModel.createCategory(data)
            }
        },
        sheetState = sheetState,
        sheetBackgroundColor = Color.Transparent,
        modifier = Modifier.zIndex(1f)
    ) {
        //need for getting up whole content above bottom bar
        RootBox() {
            CategoriesListView(viewModel, Modifier.fillMaxSize().padding(8.dp)) { category ->

            }
            FAB { scope.launch { sheetState.show() } }
        }
    }
}