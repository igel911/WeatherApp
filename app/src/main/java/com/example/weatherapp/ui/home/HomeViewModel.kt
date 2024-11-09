package com.example.weatherapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weatherapp.ui.models.ReportMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.ButtonNextEvent -> onNextClick()
            is HomeEvent.UpdateCity -> updateCity(event.value)
            is HomeEvent.UpdateDaysQuantity -> updateDaysQuantity(event.value)
            is HomeEvent.UpdateReportMode -> updateReportMode(event.reportModeName)
        }
    }

    private fun updateReportMode(reportMode: String) {
        _homeState.update { state -> state.copy(reportMode = ReportMode.valueOf(reportMode)) }
    }

    private fun updateDaysQuantity(daysQuantity: String) {
        try {
            _homeState.update { state -> state.copy(daysQuantity = daysQuantity.toInt()) }
        } catch (e: NumberFormatException) {
            // do proper logging
            Log.e("taggg", e.stackTraceToString())
        }
    }

    private fun updateCity(city: String) {
        _homeState.update { state -> state.copy(city = city) }
    }

    private fun onNextClick() {

    }
}
