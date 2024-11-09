package com.example.weatherapp.ui.home

sealed class HomeEvent {
    data class UpdateCity(val value: String): HomeEvent()
    data class UpdateDaysQuantity(val value: String): HomeEvent()
    data class UpdateReportMode(val reportModeName: String): HomeEvent()
    data object ButtonNextEvent: HomeEvent()
}
