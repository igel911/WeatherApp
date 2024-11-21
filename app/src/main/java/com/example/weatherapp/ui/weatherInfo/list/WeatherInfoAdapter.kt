package com.example.weatherapp.ui.weatherInfo.list

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.domain.models.WeatherResultItem

class WeatherInfoAdapter :
    ListAdapter<WeatherResultItem, RecyclerView.ViewHolder>(WeatherResultDiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_short_info -> ShortInfoViewHolder.create(parent)
            R.layout.item_full_info -> FullInfoViewHolder.create(parent)
            else -> {
                Log.e("tag", "unknown viewType")
                DummyViewHolder.create(parent)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is ShortInfoViewHolder -> holder.bind(getItem(position) as WeatherResultItem.ShortResult)
            is FullInfoViewHolder -> holder.bind(getItem(position) as WeatherResultItem.FullResult)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is WeatherResultItem.ShortResult -> R.layout.item_short_info
            else -> R.layout.item_full_info
        }
    }
}
