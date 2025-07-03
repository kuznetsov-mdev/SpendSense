package ru.sbx.spend_sense.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.sbx.spend_sense.platform.DeviceInfo
import ru.sbx.spend_sense.presentation.categories.data.CategoriesRepository
import ru.sbx.spend_sense.presentation.categories.list.compose.CategoriesViewModel
import ru.sbx.spend_sense.presentation.common.ui.calendar.DatePickerViewModel
import ru.sbx.spend_sense.presentation.root.RootViewModel
import ru.sbx.spend_sense.presentation.settings.SettingsViewModel
import ru.sbx.spend_sense.storage.SettingsManager

expect val platformModule: Module

object CoreModule {
    val deviceInfo = module {
        single { DeviceInfo() }
    }
}

object StorageModule {
    val settings = module {
        single { SettingsManager(get()) }
    }
}

object RepositoriesModule {
    val repositories = module {
        single { CategoriesRepository() }
    }
}

object ViewModelsModule {
    val viewModels = module {
        single { RootViewModel(get()) }
        factory { SettingsViewModel(get(), get()) }
        single { DatePickerViewModel() }
        single { CategoriesViewModel(get()) }
    }
}