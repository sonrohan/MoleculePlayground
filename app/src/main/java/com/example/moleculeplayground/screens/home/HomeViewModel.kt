package com.example.moleculeplayground.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.molecule.AndroidUiDispatcher
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor() : ViewModel() {
    private val scope = CoroutineScope(viewModelScope.coroutineContext + AndroidUiDispatcher.Main)

    private val _counter = MutableStateFlow(0)

    val state: StateFlow<HomeScreenState> = scope.launchMolecule(mode = RecompositionMode.ContextClock) {
        homeScreenPresenter(
            counterFlow = _counter,
        )
    }

    fun onAction(
        action: HomeAction,
    ) {
        when (action) {
            HomeAction.Add -> onAddClicked()
        }
    }

    private fun onAddClicked() {
        _counter.update { currentValue ->
            if (currentValue <= 9) {
                currentValue + 1
            } else {
                currentValue
            }
        }
    }
}
