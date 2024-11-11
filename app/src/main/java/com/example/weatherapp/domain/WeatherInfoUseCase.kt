package com.example.weatherapp.domain

import com.example.weatherapp.data.WeatherApi
import com.example.weatherapp.data.models.WeatherItem
import com.example.weatherapp.data.models.WeatherResource
import com.example.weatherapp.domain.models.FullResult
import com.example.weatherapp.domain.models.ShortResult
import com.example.weatherapp.domain.models.WeatherResult
import com.example.weatherapp.ui.models.ReportMode
import retrofit2.HttpException

class WeatherInfoUseCase(
    private val api: WeatherApi
) {

    suspend fun getForecast(
        city: String,
        daysQuantity: Int,
        reportMode: ReportMode
    ): Result<WeatherResult> {
        return try {
            val response = api.getForecast()
            Result.success(
                WeatherResult(
                    short = getShortResult(response, reportMode),
                    full = getFullResult(response, reportMode)
                )
            )
        } catch (e: HttpException) {
            Result.failure(e)
        }
    }

    private fun getShortResult(
        response: WeatherResource,
        reportMode: ReportMode
    ): ShortResult? {
        if (reportMode == ReportMode.FULL) return null
        val weatherItem = response.weatherItems?.firstOrNull()
        val temperature = getTemperature(weatherItem)
        val weatherType = getWeatherType(weatherItem)
        return ShortResult(temperature, weatherType)
    }


    private fun getFullResult(
        response: WeatherResource,
        reportMode: ReportMode
    ): FullResult? {
        if (reportMode == ReportMode.SHORT) return null
        val weatherItem = response.weatherItems?.firstOrNull()
        val temperature = getTemperature(weatherItem)
        val weatherType = getWeatherType(weatherItem)
        val description = weatherItem?.weatherList?.firstOrNull()?.weatherDescription ?: ""
        val humidity = weatherItem?.mainDto?.humidity ?: 0
        val pressure = weatherItem?.mainDto?.pressure ?: 0
        return FullResult(
            temperature = temperature,
            weatherType = weatherType,
            description = description,
            pressure = pressure,
            humidity = humidity
        )
    }

    private fun getTemperature(weatherItem: WeatherItem?): Double =
        weatherItem?.mainDto?.temperature ?: 0.0

    private fun getWeatherType(weatherItem: WeatherItem?): String =
        weatherItem?.weatherList?.firstOrNull()?.weatherType ?: ""
}
