package com.example.weatherapp.ui.weatherInfo.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemShortInfoBinding
import com.example.weatherapp.domain.models.WeatherResultItem
import com.example.weatherapp.ui.utils.layoutInflater

class ShortInfoViewHolder(
    private val viewBinding: ItemShortInfoBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(item: WeatherResultItem.ShortResult) {
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
