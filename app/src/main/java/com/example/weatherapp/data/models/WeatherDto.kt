package com.example.weatherapp.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    @SerialName("main") val weatherType: String? = null,
    @SerialName("description") val weatherDescription: String? = null
)
