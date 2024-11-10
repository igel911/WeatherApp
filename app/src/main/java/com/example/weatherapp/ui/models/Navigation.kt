package com.example.weatherapp.ui.models

import kotlinx.serialization.Serializable

sealed class Navigation {

    @Serializable
    data object HomeNav : Navigation()

    @Serializable
    data class WeatherInfoFragmentNav(
        val city: String,
        val daysQuantity: Int,
        val reportMode: ReportMode
    ) : Navigation()
}
