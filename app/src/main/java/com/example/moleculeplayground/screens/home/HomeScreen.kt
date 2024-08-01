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
            HomeScreenState.NotStarted -> NotStarted(
                onAddClicked = {
                    action(HomeAction.Add)
                }
            )
            is HomeScreenState.Counting -> Counting(
                count = state.count,
                onAddClicked = {
                    action(HomeAction.Add)
                }
            )
            HomeScreenState.Max -> Max()
        }
    }
}

@Composable
private fun Counting(
    modifier: Modifier = Modifier,
    count: Int,
    onAddClicked: () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "Money: $count"
        )

        AddButton(
            text = "Add",
            onAddClicked = onAddClicked,
        )
    }
}

@Composable
private fun NotStarted(
    modifier: Modifier = Modifier,
    onAddClicked: () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "Click to get started"
        )

        AddButton(
            text = "Get Started!",
            onAddClicked = onAddClicked,
        )
    }
}

@Composable
private fun Max(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = "You've maxed out your stash :("
    )
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
