package com.example.weatherapp.ui.models

data class ValidationResult(
    val isValid: Boolean = false,
    val errorMessage: String? = null
) {

    companion object {
        fun valid() = ValidationResult(isValid = true)
    }
}
