package com.example.weatherapp.ui.home.validation

import com.example.weatherapp.ui.models.ValidationResult

data class HomeValidationResult(
    val cityValidationResult: ValidationResult,
    val daysQuantityValidationResult: ValidationResult
) {
    fun isValid(): Boolean =
        listOf(
            cityValidationResult,
            daysQuantityValidationResult
        ).all { it.isValid }

    companion object {

        fun valid() = HomeValidationResult(
            cityValidationResult = ValidationResult.valid(),
            daysQuantityValidationResult = ValidationResult.valid()
        )
    }
}
