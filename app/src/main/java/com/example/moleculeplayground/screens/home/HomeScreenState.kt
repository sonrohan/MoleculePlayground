package com.example.moleculeplayground.screens.home

internal sealed interface HomeScreenState {
    data object NotStarted : HomeScreenState
    data object Max : HomeScreenState
    data class Counting(
        val count: Int,
    ) : HomeScreenState
}
