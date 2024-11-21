package com.example.weatherapp.ui.weatherInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherInfoBinding
import com.example.weatherapp.ui.models.ReportMode
import com.example.weatherapp.ui.weatherInfo.list.WeatherInfoAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherInfoFragment : Fragment(R.layout.fragment_weather_info) {

    private var _binding: FragmentWeatherInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WeatherInfoViewModel by viewModel()

    private val weatherInfoAdapter: WeatherInfoAdapter by lazy { WeatherInfoAdapter() }

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
        initUI()
        bindViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUI() {
        initTitle()
        initWeatherInfoList()
    }

    private fun initTitle() {
        binding.tvInfo.text = getCity()
    }

    private fun initWeatherInfoList() {
        binding.weatherInfoList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = weatherInfoAdapter

            val dividerItemDecoration =
                DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
            ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)
                ?.let { drawable -> dividerItemDecoration.setDrawable(drawable) }
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun bindViewModel() {
        observeViewModel()
        viewModel.getForecast(
            city = getCity(),
            daysQuantity = getDaysQuantity(),
            reportMode = getReportMode()
        )
    }

    private fun observeViewModel() {
        viewModel.shortViewState.collectLatestWithLifecycle { items ->
            items?.let { weatherInfoAdapter.submitList(it) }
        }
        viewModel.fullViewState.collectLatestWithLifecycle { items ->
            items?.let { weatherInfoAdapter.submitList(it) }
        }
        viewModel.loadingState.collectLatestWithLifecycle { isVisible ->
            binding.progress.isVisible = isVisible
        }
    }

    private fun getCity(): String? = arguments?.getString(CITY)

    private fun getDaysQuantity(): Int? = arguments?.getInt(DAYS_QUANTITY)

    private fun getReportMode(): ReportMode? =
        arguments?.getSerializable(REPORT_MODE) as? ReportMode

    private fun <T> Flow<T>.collectLatestWithLifecycle(
        lifecycle: Lifecycle = this@WeatherInfoFragment.lifecycle,
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
        collector: suspend (T) -> Unit
    ): Job = lifecycleScope.launch {
        this@collectLatestWithLifecycle
            .flowWithLifecycle(lifecycle, minActiveState)
            .collectLatest(collector)
    }

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
