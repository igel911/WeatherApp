package com.example.weatherapp.ui.weatherInfo.list

import android.view.ViewGroup
import com.example.weatherapp.databinding.ItemShortInfoBinding
import com.example.weatherapp.domain.models.ShortResult
import com.example.weatherapp.ui.utils.layoutInflater
import com.example.weatherapp.ui.weatherInfo.WeatherInfoViewHolder

class ShortInfoViewHolder(
    private val viewBinding: ItemShortInfoBinding
) : WeatherInfoViewHolder<ShortResult>(viewBinding) {

    override fun bind(item: ShortResult) {
        with(viewBinding) {
            tvTemperatureValue.text = item.temperature.toString()
            tvWeatherTypeValue.text = item.weatherType
        }
    }

    companion object {

        fun create(
            parent: ViewGroup
        ): ShortInfoViewHolder {
            val binding = ItemShortInfoBinding.inflate(parent.context.layoutInflater, parent, false)
            return ShortInfoViewHolder(binding)
        }
    }
}
