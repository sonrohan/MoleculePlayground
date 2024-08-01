package com.example.moleculeplayground.screens.home

import com.example.moleculeplayground.components.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor() : BaseViewModel<HomeAction, HomeScreenState>() {

    private val _counter = MutableStateFlow(0)

    override val state: StateFlow<HomeScreenState> = createPresenter {
        homeScreenPresenter(
            counterFlow = _counter,
        )
    }

    override fun onAction(
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
