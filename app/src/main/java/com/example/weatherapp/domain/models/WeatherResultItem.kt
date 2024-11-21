package com.example.weatherapp.domain.models

sealed class WeatherResultItem(open val id: String) {
    data class ShortResult(
        override val id: String,
        val temperature: Double,
        val weatherType: String
    ) : WeatherResultItem(id)

    data class FullResult(
        override val id: String,
        val temperature: Double,
        val weatherType: String,
        val description: String,
        val pressure: Int,
        val humidity: Int
    ) : WeatherResultItem(id)
}
