package com.example.weatherapp.di

import com.example.weatherapp.ui.home.HomeViewModel
import com.example.weatherapp.ui.home.validation.HomeValidator
import com.example.weatherapp.ui.utils.resource_provider.ResourceProvider
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
}

val utilModule = module {
    singleOf(::ResourceProvider)
}

val useCaseModule = module {

}

val repositoryModule = module {

}

val validatorModule = module {
    singleOf(::HomeValidator)
}
