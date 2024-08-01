package com.example.moleculeplayground.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    HomeScreen(
        state = state,
    )
}

@Composable
private fun HomeScreen(
    state: HomeScreenState,
) {
    Text("$state")
}
