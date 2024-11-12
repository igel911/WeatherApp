package com.example.weatherapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.weatherapp.ui.composables.AndroidViewFragment
import com.example.weatherapp.ui.home.HomeScreen
import com.example.weatherapp.ui.models.Navigation
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.weatherInfo.WeatherInfoFragment

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Navigation.HomeNav) {
                    composable<Navigation.HomeNav> { HomeScreen(navController) }
                    //fragment<WeatherInfoFragment>("weatherInfoFragment") { WeatherInfoFragment() }
                    composable<Navigation.WeatherInfoFragmentNav> {
                        val data = it.toRoute<Navigation.WeatherInfoFragmentNav>()
                        AndroidViewFragment(
                            modifier = Modifier.fillMaxSize(),
                            fragmentId = R.id.fl_fragment_number_two,
                            fragment = WeatherInfoFragment.newInstance(
                                city = data.city,
                                daysQuantity = data.daysQuantity,
                                reportMode = data.reportMode
                            ),
                            fragmentManager = supportFragmentManager,
                            backPressHandler = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
