package com.example.moleculeplayground.screens.home

import app.cash.molecule.RecompositionMode
import app.cash.molecule.moleculeFlow
import app.cash.turbine.test
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeScreenPresenterTests {
    @Test
    fun `homeScreenPresenter emits correct states`() = runTest {
        val counter = Channel<Int>()

        moleculeFlow(RecompositionMode.Immediate) {
            homeScreenPresenter(
                counter.receiveAsFlow(),
            )
        }.test {
            assertEquals(HomeScreenState.NotStarted, awaitItem())
            counter.send(1)
            assertEquals(HomeScreenState.Counting(1), awaitItem())
            counter.send(2)
            assertEquals(HomeScreenState.Counting(2), awaitItem())
            counter.send(3)
            assertEquals(HomeScreenState.Counting(3), awaitItem())
            counter.send(9)
            assertEquals(HomeScreenState.Counting(9), awaitItem())
            counter.send(10)
            assertEquals(HomeScreenState.Max, awaitItem())
        }
    }
}