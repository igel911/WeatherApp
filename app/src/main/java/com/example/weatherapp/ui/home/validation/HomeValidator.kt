package com.example.weatherapp.ui.home.validation

import androidx.core.text.isDigitsOnly
import com.example.weatherapp.R
import com.example.weatherapp.ui.home.HomeState
import com.example.weatherapp.ui.models.ValidationResult
import com.example.weatherapp.ui.utils.ResourceProvider

class HomeValidator(
    private val resourceProvider: ResourceProvider
) {

    fun validate(state: HomeState): HomeValidationResult {
        val cityValidationResult = validateCity(state.city)
        val daysQuantityValidationResult = validateDaysQuantity(state.daysQuantityStr)
        return HomeValidationResult(
            cityValidationResult = cityValidationResult,
            daysQuantityValidationResult = daysQuantityValidationResult
        )
    }

    private fun validateCity(city: String): ValidationResult {
        return if (city.isBlank()) {
            ValidationResult(errorMessage = resourceProvider.getString(R.string.city_empty))
        } else {
            ValidationResult.valid()
        }
    }

    private fun validateDaysQuantity(daysQuantityStr: String): ValidationResult {
        return when {
            daysQuantityStr.isEmpty() -> ValidationResult(errorMessage = resourceProvider.getString(R.string.days_quantity_is_empty))
            !daysQuantityStr.isDigitsOnly() -> ValidationResult(errorMessage = resourceProvider.getString(R.string.days_quantity_bad_format))
            daysQuantityStr.startsWith("0") -> ValidationResult(errorMessage = resourceProvider.getString(R.string.days_quantity_too_small))
             else -> ValidationResult.valid()
        }
    }
}
