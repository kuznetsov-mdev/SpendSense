package ru.sbx.spend_sense.di

import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue
import org.koin.dsl.module
import org.koin.ext.getFullName
import ru.sbx.spend_sense.data.CategoriesRepository
import ru.sbx.spend_sense.data.EventsRepository
import ru.sbx.spend_sense.data.storage.SettingsManager
import ru.sbx.spend_sense.platform.DeviceInfo
import ru.sbx.spend_sense.presentation.categories.list.compose.CategoriesViewModel
import ru.sbx.spend_sense.presentation.common.ui.calendar.DatePickerViewModel
import ru.sbx.spend_sense.presentation.events.creation.EventCreationViewModel
import ru.sbx.spend_sense.presentation.events.list.compose.EventsViewModel
import ru.sbx.spend_sense.presentation.root.RootViewModel
import ru.sbx.spend_sense.presentation.settings.SettingsViewModel

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
        single { EventsRepository() }
    }
}

object ViewModelsModule {
    val viewModels = module {
        single { RootViewModel(get()) }
        factory { SettingsViewModel(get(), get()) }
        single(DatePickerSingleQualifier) { DatePickerViewModel() }
        factory(DatePickerFactoryQualifier) { DatePickerViewModel() }
        single { CategoriesViewModel(get()) }
        single { EventsViewModel(get(), get()) }
        single { EventCreationViewModel() }
    }
}

object DatePickerSingleQualifier : Qualifier {
    override val value: QualifierValue
        get() = this::class.getFullName()

}

object DatePickerFactoryQualifier : Qualifier {
    override val value: QualifierValue
        get() = this::class.getFullName()

}