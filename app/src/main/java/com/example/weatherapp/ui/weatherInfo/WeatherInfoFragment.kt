package com.example.weatherapp.ui.weatherInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherInfoBinding
import com.example.weatherapp.ui.models.ReportMode

class WeatherInfoFragment: Fragment(R.layout.fragment_weather_info) {

    private var _binding: FragmentWeatherInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvInfo.text = "city = ${getCity()}, daysQuantity = ${getDaysQuantity()}, ReportMode = ${getReportMode()}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getCity(): String? = arguments?.getString(CITY)

    private fun getDaysQuantity(): Int? = arguments?.getInt(DAYS_QUANTITY)

    private fun getReportMode(): ReportMode? = arguments?.getSerializable(REPORT_MODE) as? ReportMode

    companion object {
        private const val CITY = "CITY"
        private const val DAYS_QUANTITY = "DAYS_QUANTITY"
        private const val REPORT_MODE = "REPORT_MODE"
        fun newInstance(
            city: String,
            daysQuantity: Int,
            reportMode: ReportMode
        ): WeatherInfoFragment {
            return WeatherInfoFragment().apply {
                arguments = bundleOf(
                    CITY to city,
                    DAYS_QUANTITY to daysQuantity,
                    REPORT_MODE to reportMode
                )
            }
        }
    }
}
