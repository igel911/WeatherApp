package com.example.weatherapp.domain.models

data class FullResult(
    val temperature: Double,
    val weatherType: String,
    val description: String,
    val pressure: Int,
    val humidity: Int
) : AbstractWeatherResult()
