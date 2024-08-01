package com.example.moleculeplayground.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    HomeScreen(
        state = state,
        action = viewModel::onAction,
    )
}

@Composable
private fun HomeScreen(
    state: HomeScreenState,
    action: (HomeAction) -> Unit,
) {
    Column {
        when (state) {
            is HomeScreenState.Counting -> {
                Text(
                    text = "Money: ${state.count}"
                )

                AddButton(
                    text = "Add",
                    onAddClicked = {
                        action(HomeAction.Add)
                    }
                )
            }
            HomeScreenState.Max -> {
                Text(
                    text = "You've maxed out your stash :("
                )
            }
            HomeScreenState.NotStarted -> {
                Text(
                    text = "Click to get started"
                )

                AddButton(
                    text = "Get Started!",
                    onAddClicked = {
                        action(HomeAction.Add)
                    }
                )
            }
        }
    }
}

@Composable
private fun AddButton(
    modifier: Modifier = Modifier,
    text: String,
    onAddClicked: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onAddClicked,
        content = {
            Text(
                text = text,
            )
        }
    )
}
