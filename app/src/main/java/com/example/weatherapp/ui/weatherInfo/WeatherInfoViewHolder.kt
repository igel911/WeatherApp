package com.example.weatherapp.ui.weatherInfo

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.weatherapp.domain.models.AbstractWeatherResult

abstract class WeatherInfoViewHolder<I : AbstractWeatherResult>(
    binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: I)
}
