package com.example.weatherapp.domain.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatcherProvider {
     val io: CoroutineDispatcher = Dispatchers.IO
     val main: CoroutineDispatcher = Dispatchers.Main
     val default: CoroutineDispatcher = Dispatchers.Default
}
