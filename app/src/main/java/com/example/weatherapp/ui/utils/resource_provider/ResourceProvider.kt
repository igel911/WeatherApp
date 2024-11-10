package com.example.weatherapp.ui.utils.resource_provider

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

class ResourceProvider(private val context: Context) {

    fun getString(@StringRes stringId: Int): String =
        context.getString(stringId)

    fun getString(@StringRes stringId: Int, vararg args: Any?): String =
        context.getString(stringId, *args)

    fun getArray(@ArrayRes arrayId: Int): Array<String> =
        context.resources.getStringArray(arrayId)
}
