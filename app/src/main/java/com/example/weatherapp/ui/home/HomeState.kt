package com.example.weatherapp.ui.home

import com.example.weatherapp.ui.utils.validation.HomeValidationResult
import com.example.weatherapp.ui.models.ReportMode

data class HomeState(
    val city: String = "",
    val daysQuantityStr: String = "",
    val reportMode: ReportMode = ReportMode.FULL,
    val validationResult: HomeValidationResult = HomeValidationResult.valid()
) {
    val reportModes: List<String> = ReportMode.entries.toTypedArray().map { it.name }
    val daysQuantity = try {
        daysQuantityStr.toInt()
    } catch (e: NumberFormatException) {
        0
    }
}
