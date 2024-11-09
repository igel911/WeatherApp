package com.example.weatherapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.R
import com.example.weatherapp.ui.composables.TextInput
import com.example.weatherapp.ui.composables.VerticalSpacer
import com.example.weatherapp.ui.theme.WeatherAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel: HomeViewModel = viewModel()
    val homeState by viewModel.homeState.collectAsState()
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            TextInput(
                value = homeState.city,
                onValueChange = { newText ->
                    viewModel.onEvent(HomeEvent.UpdateCity(newText))
                },
                placeholderText = stringResource(R.string.city),
                modifier = Modifier.fillMaxWidth()
            )

            VerticalSpacer()

            TextInput(
                value = homeState.daysQuantity.toString(),
                onValueChange = { newText ->
                    if (newText.all { it.isDigit() }) {
                        viewModel.onEvent(HomeEvent.UpdateDaysQuantity(newText))
                    }
                },
                placeholderText = stringResource(R.string.days),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            VerticalSpacer()

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextInput(
                    value = homeState.reportMode.name,
                    onValueChange = {},
                    readOnly = true,
                    placeholderText = stringResource(R.string.report_mode),
                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                        .fillMaxWidth(),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    textFieldColors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    homeState.reportModes.forEachIndexed { index, option ->
                        DropdownMenuItem(
                            onClick = {
                                viewModel.onEvent(HomeEvent.UpdateReportMode(option))
                                expanded = false
                            },
                            text = { Text(text = option) }
                        )
                        if (index != homeState.reportModes.lastIndex) {
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    WeatherAppTheme {
        HomeScreen()
    }
}
