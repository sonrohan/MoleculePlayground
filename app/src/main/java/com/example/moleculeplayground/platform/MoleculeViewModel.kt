package com.example.moleculeplayground.platform

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.molecule.AndroidUiDispatcher
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class MoleculeViewModel<Event, State> : ViewModel() {
    private val scope =
        CoroutineScope(
            context = viewModelScope.coroutineContext + AndroidUiDispatcher.Main,
        )

    private val events: Channel<Event> =
        Channel(
            capacity = 20,
            onBufferOverflow = BufferOverflow.SUSPEND,
        )

    private val eventsFlow: Flow<Event> = events.receiveAsFlow()

    val models: StateFlow<State> by lazy(LazyThreadSafetyMode.NONE) {
        scope.launchMolecule(mode = RecompositionMode.ContextClock) {
            models(eventsFlow)
        }
    }

    @Composable
    protected abstract fun models(events: Flow<Event>): State

    fun onEvent(event: Event) {
        if (events.trySend(event).isFailure) {
            error("Event buffer overflow on event:$event.")
        }
    }
}
