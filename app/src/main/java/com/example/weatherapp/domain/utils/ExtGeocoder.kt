package com.example.weatherapp.domain.utils

import android.location.Address
import android.location.Geocoder
import android.os.Build
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

suspend fun Geocoder.awaitFromLocationName(
    locationName: String,
    maxResults: Int = 1
): List<Address>? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        suspendCancellableCoroutine { continuation ->
            getFromLocationName(locationName, maxResults) { addresses ->
                continuation.resume(addresses)
            }
        }
    } else {
        @Suppress("DEPRECATION")
        getFromLocationName(locationName, maxResults)
    }
}
