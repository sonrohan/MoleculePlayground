package com.example.moleculeplayground.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.Flow

@Composable
internal fun homeScreenPresenter(
    counterFlow: Flow<Int>,
): HomeScreenState {
    val count by counterFlow.collectAsState(initial = 0)

    return when {
        count == 0 -> HomeScreenState.NotStarted
        count <= 9 -> HomeScreenState.Counting(count)
        count > 9 -> HomeScreenState.Max
        else -> HomeScreenState.Max
    }
}