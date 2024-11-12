package com.example.weatherapp.ui.weatherInfo.list

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.domain.models.AbstractWeatherResult
import com.example.weatherapp.domain.models.FullResult
import com.example.weatherapp.domain.models.ShortResult
import com.example.weatherapp.ui.weatherInfo.WeatherInfoViewHolder

class WeatherInfoAdapter : RecyclerView.Adapter<WeatherInfoViewHolder<*>>() {
    private var items = emptyList<AbstractWeatherResult>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherInfoViewHolder<*> {
        return when (viewType) {
            R.layout.item_short_info -> ShortInfoViewHolder.create(parent)
            R.layout.item_full_info -> FullInfoViewHolder.create(parent)
            else -> throw IllegalArgumentException("unknown viewType")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(
        holder: WeatherInfoViewHolder<*>,
        position: Int
    ) {
        when (holder) {
            is ShortInfoViewHolder -> holder.bind(items[position] as ShortResult)
            is FullInfoViewHolder -> holder.bind(items[position] as FullResult)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ShortResult -> R.layout.item_short_info
            else -> R.layout.item_full_info
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<AbstractWeatherResult>) {
        items = list
        notifyDataSetChanged()
    }
}
