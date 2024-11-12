package com.example.weatherapp.ui.weatherInfo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.WeatherInfoUseCase
import com.example.weatherapp.domain.models.AbstractWeatherResult
import com.example.weatherapp.ui.models.ReportMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherInfoViewModel(
    private val weatherInfoUseCase: WeatherInfoUseCase
) : ViewModel() {

    private val _shortViewState = MutableStateFlow<List<AbstractWeatherResult>?>(null)
    val shortViewState = _shortViewState.asStateFlow()

    private val _fullViewState = MutableStateFlow<List<AbstractWeatherResult>?>(null)
    val fullViewState = _fullViewState.asStateFlow()

    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

    fun getForecast(
        city: String?,
        daysQuantity: Int?,
        reportMode: ReportMode?
    ) {
        if (city != null && daysQuantity != null && reportMode != null) {
            viewModelScope.launch {
                _loadingState.update { true }
                val forecastResult = weatherInfoUseCase.getForecast(city, daysQuantity, reportMode)
                if (forecastResult.isSuccess) {
                    _fullViewState.update { forecastResult.getOrNull()?.fullList }
                    _shortViewState.update { forecastResult.getOrNull()?.shortList }
                } else {
                    // do proper logging
                    Log.e("taggg", "${forecastResult.exceptionOrNull()?.message}")
                }
                _loadingState.update { false }
            }
        }
    }
}
