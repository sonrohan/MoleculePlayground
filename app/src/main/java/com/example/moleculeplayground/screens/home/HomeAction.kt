package com.example.moleculeplayground.screens.home

internal sealed interface HomeAction {
    data object Add: HomeAction
}