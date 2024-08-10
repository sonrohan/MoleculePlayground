package com.example.moleculeplayground.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.Flow

@Composable
internal fun homeScreenPresenter(
    events: Flow<HomeEvent>,
): HomeModel {
    var counter by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                HomeEvent.Add -> {
                    if (counter <= 9) {
                        counter += 1
                    }
                }
            }
        }
    }

    return when {
        counter == 0 -> HomeModel.NotStarted
        counter <= 9 -> HomeModel.Counting(counter)
        counter > 9 -> HomeModel.Max
        else -> HomeModel.Max
    }
}