package ru.sbx.spend_sense.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue
import org.koin.dsl.module
import org.koin.ext.getFullName
import ru.sbx.spend_sense.data.CategoriesRepository
import ru.sbx.spend_sense.data.EventsRepository
import ru.sbx.spend_sense.data.network.AppApi
import ru.sbx.spend_sense.data.storage.DbAdapters
import ru.sbx.spend_sense.data.storage.SettingsManager
import ru.sbx.spend_sense.data.storage.dao.CategoryDao
import ru.sbx.spend_sense.data.storage.dao.SpendEventDao
import ru.sbx.spend_sense.db.AppDb
import ru.sbx.spend_sense.extentions.appLog
import ru.sbx.spend_sense.platform.DeviceInfo
import ru.sbx.spend_sense.presentation.auth.register.RegisterViewModel
import ru.sbx.spend_sense.presentation.auth.signin.SignInViewModel
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
        factory { Dispatchers.Default + SupervisorJob() }
    }
}

object NetworkModule {
    val json = module {
        single {
            Json {
                encodeDefaults = false //fields with default values won't be serialized
                isLenient = true // ignore some mistake like absent of " around keys
                ignoreUnknownKeys = true //
                explicitNulls = false // null won't be serialized
                prettyPrint = true
            }
        }
    }

    val httpClient = module {
        single {
            HttpClient(CIO) {
                expectSuccess = false // if true - will throw an Exception for response with code 4xx
                install(ContentNegotiation) {//for connect feature-module to client
                    json(get())
                }
                install(Logging) {
                    level = LogLevel.ALL
                    logger = object : Logger {
                        override fun log(message: String) {
                            appLog(message)
                        }

                    }
                }
            }
        }
    }

    val appApi = module { single { AppApi(get(), get()) } }
}

object StorageModule {
    val settings = module {
        single { SettingsManager(get()) }
    }
    val db = module {
        single {
            AppDb(get(), DbAdapters.categoryDbAdapter, DbAdapters.eventDbAdapter)
        }
    }
    val dao = module {
        single { CategoryDao(get(), get()) }
        single { SpendEventDao(get(), get()) }
    }
}

object RepositoriesModule {
    val repositories = module {
        single { CategoriesRepository(get()) }
        single { EventsRepository(get()) }
    }
}

object ViewModelsModule {
    val viewModels = module {
        single { RootViewModel(get()) }
        factory { SettingsViewModel(get(), get()) }
        single(DatePickerSingleQualifier) { DatePickerViewModel() }
        factory(DatePickerFactoryQualifier) { DatePickerViewModel() }
        factory { EventsViewModel(get(), get()) }
        single { CategoriesViewModel(get()) }
        factory { EventCreationViewModel() }
        factory { RegisterViewModel(get(), get()) }
        factory { SignInViewModel(get(), get()) }
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