package com.example.weatherapp.domain.models

data class WeatherResult(
    val shortList: List<ShortResult>? = null,
    val fullList: List<FullResult>? = null
)
