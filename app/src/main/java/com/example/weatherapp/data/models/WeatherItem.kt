package com.example.weatherapp.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherItem(
    @SerialName("main") val mainDto: MainDto? = null,
    @SerialName("weather") val weatherList: List<WeatherDto>? = null
)
