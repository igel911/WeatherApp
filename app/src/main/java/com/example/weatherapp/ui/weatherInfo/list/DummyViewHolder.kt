package com.example.weatherapp.ui.weatherInfo.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemDummyBinding
import com.example.weatherapp.ui.utils.layoutInflater

class DummyViewHolder(
    viewBinding: ItemDummyBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

    companion object {

        fun create(
            parent: ViewGroup
        ): DummyViewHolder {
            val binding = ItemDummyBinding.inflate(parent.context.layoutInflater, parent, false)
            return DummyViewHolder(binding)
        }
    }
}
