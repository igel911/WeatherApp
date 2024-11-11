package com.example.weatherapp.data

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.models.WeatherResource
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/forecast")
    suspend fun getForecast(
        @Query("lat") latitude: Double = 33.44,
        @Query("lon") longitude: Double = -94.04,
        @Query("appid") apikey: String = BuildConfig.WEATHER_API_KEY,
        @Query("units") units: String = "metric"
    ): WeatherResource
}
