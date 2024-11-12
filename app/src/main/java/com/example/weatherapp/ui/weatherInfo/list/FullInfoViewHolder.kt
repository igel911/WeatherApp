package com.example.weatherapp.ui.weatherInfo.list

import android.view.ViewGroup
import com.example.weatherapp.databinding.ItemFullInfoBinding
import com.example.weatherapp.domain.models.FullResult
import com.example.weatherapp.ui.utils.layoutInflater
import com.example.weatherapp.ui.weatherInfo.WeatherInfoViewHolder

class FullInfoViewHolder(
    private val viewBinding: ItemFullInfoBinding
) : WeatherInfoViewHolder<FullResult>(viewBinding) {

    override fun bind(item: FullResult) {
        with(viewBinding) {
            tvTemperatureValue.text = item.temperature.toString()
            tvWeatherTypeValue.text = item.weatherType
            tvDescriptionValue.text = item.description
            tvHumidityValue.text = item.humidity.toString()
            tvPressureValue.text = item.pressure.toString()
        }
    }

    companion object {

        fun create(
            parent: ViewGroup
        ): FullInfoViewHolder {
            val binding = ItemFullInfoBinding.inflate(parent.context.layoutInflater, parent, false)
            return FullInfoViewHolder(binding)
        }
    }
}
