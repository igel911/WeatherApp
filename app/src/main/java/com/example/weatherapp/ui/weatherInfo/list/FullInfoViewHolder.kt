package com.example.weatherapp.ui.weatherInfo.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemFullInfoBinding
import com.example.weatherapp.domain.models.WeatherResultItem
import com.example.weatherapp.ui.utils.layoutInflater

class FullInfoViewHolder(
    private val viewBinding: ItemFullInfoBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(item: WeatherResultItem.FullResult) {
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
