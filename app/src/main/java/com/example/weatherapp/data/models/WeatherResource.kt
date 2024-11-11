package com.example.weatherapp.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResource(
    @SerialName("list") val weatherItems: List<WeatherItem>? = null,
    @SerialName("city") val city: CityDto? = null
)
