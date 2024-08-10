package com.example.moleculeplayground.screens.home

internal sealed interface HomeEvent {
    data object Add: HomeEvent
}