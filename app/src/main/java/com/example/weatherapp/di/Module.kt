package com.example.weatherapp.di

import android.content.Context
import android.location.Geocoder
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.WeatherApi
import com.example.weatherapp.domain.WeatherInfoUseCase
import com.example.weatherapp.domain.utils.DispatcherProvider
import com.example.weatherapp.ui.home.HomeViewModel
import com.example.weatherapp.ui.home.validation.HomeValidator
import com.example.weatherapp.ui.utils.ResourceProvider
import com.example.weatherapp.ui.weatherInfo.WeatherInfoViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.Locale


val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::WeatherInfoViewModel)
}

val utilModule = module {
    singleOf(::ResourceProvider)
    singleOf(::DispatcherProvider)
    factory { provideGeocoder(get()) }
}

val useCaseModule = module {
    singleOf(::WeatherInfoUseCase)
}

val apiModule = module {
    factory { provideWeatherApi(get()) }
    single { provideRetrofit() }
}

val validatorModule = module {
    singleOf(::HomeValidator)
}

fun provideRetrofit(): Retrofit {
    val json = Json { ignoreUnknownKeys = true }
    return Retrofit.Builder()
        .baseUrl(BuildConfig.WEATHER_URL)
        .addConverterFactory(
            json.asConverterFactory(
                "application/json; charset=UTF8".toMediaType()))
        .build()
}

fun provideWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)

fun provideGeocoder(context: Context): Geocoder {
    return Geocoder(context, Locale.getDefault())
}

