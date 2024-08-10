package com.example.moleculeplayground.screens.home

internal sealed interface HomeModel {
    data object NotStarted : HomeModel
    data object Max : HomeModel
    data class Counting(
        val count: Int,
    ) : HomeModel
}
