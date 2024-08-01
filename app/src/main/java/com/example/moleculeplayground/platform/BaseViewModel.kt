package com.example.moleculeplayground.platform

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.molecule.AndroidUiDispatcher
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<Action, State> : ViewModel() {
    private val scope = CoroutineScope(
        context = viewModelScope.coroutineContext + AndroidUiDispatcher.Main,
    )

    abstract val state: StateFlow<State>

    fun <T> createPresenter (
        body: @Composable () -> T,
    ) = scope.launchMolecule(
        mode = RecompositionMode.ContextClock,
    ) { body() }

    abstract fun onAction(action: Action)
}
