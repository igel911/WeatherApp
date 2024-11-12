package com.example.weatherapp.ui.utils

import android.content.Context
import android.view.LayoutInflater

inline val Context.layoutInflater: LayoutInflater
    get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
