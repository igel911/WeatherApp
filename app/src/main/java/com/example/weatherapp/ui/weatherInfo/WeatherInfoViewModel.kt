package com.example.weatherapp.ui.weatherInfo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.WeatherInfoUseCase
import com.example.weatherapp.domain.models.FullResult
import com.example.weatherapp.domain.models.ShortResult
import com.example.weatherapp.ui.home.HomeState
import com.example.weatherapp.ui.models.ReportMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherInfoViewModel(
    private val weatherInfoUseCase: WeatherInfoUseCase
): ViewModel() {

    private val _shortViewState = MutableStateFlow<ShortResult?>(null)
    val shortViewState = _shortViewState.asStateFlow()

    private val _fullViewState = MutableStateFlow<FullResult?>(null)
    val fullViewState = _fullViewState.asStateFlow()

    fun getForecast(
        city: String?,
        daysQuantity: Int?,
        reportMode: ReportMode?
    ) {
        if (city != null && daysQuantity != null && reportMode != null) {
            viewModelScope.launch {
                val forecastResult = weatherInfoUseCase.getForecast(city, daysQuantity, reportMode)
                Log.d("taggg", "forecastResult = $forecastResult")
                if (forecastResult.isSuccess) {
                    _fullViewState.update { forecastResult.getOrNull()?.full }
                    _shortViewState.update { forecastResult.getOrNull()?.short }
                } else {
                    // do proper logging
                    Log.e("taggg", "${forecastResult.exceptionOrNull()?.message}")
                }
            }
        }
    }
}
