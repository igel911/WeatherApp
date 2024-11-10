package com.example.weatherapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.ui.home.validation.HomeValidator
import com.example.weatherapp.ui.models.ReportMode
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeValidator: HomeValidator
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    private val _openDetailsEvent = Channel<HomeState>()
    val openDetailsEvent = _openDetailsEvent.receiveAsFlow()

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
        _homeState.update { state ->
            val validationResult = homeValidator.validate(state)
            state.copy(validationResult = validationResult).also {
                if (validationResult.isValid()) {
                    openDetails(state)
                }
            }
        }
    }

    private fun openDetails(state: HomeState) {
        viewModelScope.launch {
            _openDetailsEvent.send(state)
        }
    }
}
