package com.example.weatherapp.ui.composables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.ui.models.ValidationResult
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun TextInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholderText: String = "",
    validationResult: ValidationResult = ValidationResult.valid(),
    singleLine: Boolean = true,
    readOnly: Boolean = false,
    maxLines: Int = 1,
    trailingIcon: @Composable (() -> Unit)?  = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.None
    ),
    textStyle: TextStyle = TextStyle(textAlign = TextAlign.Start),
    textColor: Color = Color.Black,
    textFieldColors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = textColor
    )
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholderText

            )
        },
        singleLine = singleLine,
        readOnly = readOnly,
        maxLines = maxLines,
        keyboardOptions = keyboardOptions,
        textStyle = textStyle,
        colors = textFieldColors,
        isError = !validationResult.isValid,
        trailingIcon = trailingIcon,
        supportingText = if (!validationResult.isValid) {
            @Composable {
                Text(
                    text = validationResult.errorMessage.orEmpty(),
                    color = Color.Red
                )
            }
        } else null
    )
}

@Preview(showBackground = true)
@Composable
private fun OutlinedTextInputPreview() {
    WeatherAppTheme {
        TextInput(
            value = "abc",
            onValueChange = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OutlinedTextInputErrorPreview() {
    WeatherAppTheme {
        TextInput(
            value = "abc",
            onValueChange = { },
            validationResult = ValidationResult(
                isValid = false,
                errorMessage = "Houston, we have a problem"
            )
        )
    }
}
