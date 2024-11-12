package com.example.weatherapp.domain.models

data class ShortResult(
    val temperature: Double,
    val weatherType: String
) : AbstractWeatherResult()
