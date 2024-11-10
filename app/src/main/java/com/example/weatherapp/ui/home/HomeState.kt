package com.example.weatherapp.ui.home

import com.example.weatherapp.ui.home.validation.HomeValidationResult
import com.example.weatherapp.ui.models.ReportMode

data class HomeState(
    val city: String = "",
    val daysQuantity: Int = 0,
    val reportMode: ReportMode = ReportMode.FULL,
    val validationResult: HomeValidationResult = HomeValidationResult.valid()
) {
    val reportModes: List<String> = ReportMode.entries.toTypedArray().map { it.name }
}
