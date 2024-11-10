package com.example.weatherapp.ui.composables

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager

@Composable
fun AndroidViewFragment(
    modifier: Modifier = Modifier,
    fragmentId: Int,
    fragment: Fragment,
    fragmentManager: FragmentManager,
    backPressHandler: () -> Unit
) {
    BackHandler {
        backPressHandler()
    }
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val frgId = FragmentContainerView(context).apply {
                id = fragmentId
            }
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(fragmentId, fragment, fragment.javaClass.simpleName)
            transaction.addToBackStack(null)
            transaction.commit()
            frgId
        },
        update = {
            //Do not put the fragment update here, the fragment will be created multiple times
        }
    )
}
