package com.example.weatherapp.domain.models

data class WeatherResult(
    val shortList: List<WeatherResultItem.ShortResult>? = null,
    val fullList: List<WeatherResultItem.FullResult>? = null
)
