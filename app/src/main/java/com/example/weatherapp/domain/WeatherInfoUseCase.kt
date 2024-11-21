package com.example.weatherapp.domain

import android.location.Geocoder
import com.example.weatherapp.data.WeatherApi
import com.example.weatherapp.data.models.WeatherItem
import com.example.weatherapp.data.models.WeatherResource
import com.example.weatherapp.domain.models.WeatherResult
import com.example.weatherapp.domain.models.WeatherResultItem
import com.example.weatherapp.domain.utils.DispatcherProvider
import com.example.weatherapp.domain.utils.awaitFromLocationName
import com.example.weatherapp.ui.models.ReportMode
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.UUID

class WeatherInfoUseCase(
    private val api: WeatherApi,
    private val geocoder: Geocoder,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend fun getForecast(
        city: String,
        daysQuantity: Int,
        reportMode: ReportMode
    ): Result<WeatherResult> {
        return withContext(dispatcherProvider.io) {
            val address = geocoder
                .awaitFromLocationName(city)
                .orEmpty()
                .firstOrNull()

            address?.let {
                try {
                    val response = api.getForecast(
                        latitude = address.latitude,
                        longitude = address.longitude
                    )
                    Result.success(
                        WeatherResult(
                            shortList = getShortResult(response, reportMode, daysQuantity),
                            fullList = getFullResult(response, reportMode, daysQuantity)
                        )
                    )
                } catch (e: HttpException) {
                    Result.failure(e)
                }
            } ?: Result.failure(NullPointerException("address is null"))

        }
    }

    private fun getShortResult(
        response: WeatherResource,
        reportMode: ReportMode,
        daysQuantity: Int
    ): List<WeatherResultItem.ShortResult>? {
        if (reportMode == ReportMode.FULL) return null
        return response.weatherItems?.take(daysQuantity)?.map { weatherItem ->
            val temperature = getTemperature(weatherItem)
            val weatherType = getWeatherType(weatherItem)
            WeatherResultItem.ShortResult(
                id = UUID.randomUUID().toString(),
                temperature = temperature,
                weatherType = weatherType
            )
        }
    }


    private fun getFullResult(
        response: WeatherResource,
        reportMode: ReportMode,
        daysQuantity: Int
    ): List<WeatherResultItem.FullResult>? {
        if (reportMode == ReportMode.SHORT) return null
        return response.weatherItems?.take(daysQuantity)?.map { weatherItem ->
            val temperature = getTemperature(weatherItem)
            val weatherType = getWeatherType(weatherItem)
            val description = weatherItem.weatherList?.firstOrNull()?.weatherDescription ?: ""
            val humidity = weatherItem.mainDto?.humidity ?: 0
            val pressure = weatherItem.mainDto?.pressure ?: 0
            WeatherResultItem.FullResult(
                id = UUID.randomUUID().toString(),
                temperature = temperature,
                weatherType = weatherType,
                description = description,
                pressure = pressure,
                humidity = humidity
            )
        }
    }

    private fun getTemperature(weatherItem: WeatherItem?): Double =
        weatherItem?.mainDto?.temperature ?: 0.0

    private fun getWeatherType(weatherItem: WeatherItem?): String =
        weatherItem?.weatherList?.firstOrNull()?.weatherType ?: ""
}
