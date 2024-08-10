package com.example.moleculeplayground.screens.home

import androidx.compose.runtime.Composable
import com.example.moleculeplayground.platform.MoleculeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor()
    : MoleculeViewModel<HomeEvent, HomeModel>() {
    @Composable
    override fun models(events: Flow<HomeEvent>): HomeModel {
        return homeScreenPresenter(
            events = events,
        )
    }
}
