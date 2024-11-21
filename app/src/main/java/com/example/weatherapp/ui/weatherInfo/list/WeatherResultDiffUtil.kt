package com.example.weatherapp.ui.weatherInfo.list

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherapp.domain.models.WeatherResultItem

object WeatherResultDiffUtil : DiffUtil.ItemCallback<WeatherResultItem>() {

    override fun areItemsTheSame(
        oldItem: WeatherResultItem,
        newItem: WeatherResultItem
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: WeatherResultItem,
        newItem: WeatherResultItem
    ): Boolean =
        oldItem == newItem
}
