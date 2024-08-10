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
        val counter = Channel<HomeEvent>()

        moleculeFlow(RecompositionMode.Immediate) {
            homeScreenPresenter(
                counter.receiveAsFlow(),
            )
        }.test {
            // Initial State
            assertEquals(HomeModel.NotStarted, awaitItem())

            counter.send(HomeEvent.Add)
            assertEquals(HomeModel.Counting(1), awaitItem())

            counter.send(HomeEvent.Add)
            assertEquals(HomeModel.Counting(2), awaitItem())

            counter.send(HomeEvent.Add)
            assertEquals(HomeModel.Counting(3), awaitItem())

            counter.send(HomeEvent.Add)
            assertEquals(HomeModel.Counting(4), awaitItem())

            counter.send(HomeEvent.Add)
            assertEquals(HomeModel.Counting(5), awaitItem())

            counter.send(HomeEvent.Add)
            assertEquals(HomeModel.Counting(6), awaitItem())

            counter.send(HomeEvent.Add)
            assertEquals(HomeModel.Counting(7), awaitItem())

            counter.send(HomeEvent.Add)
            assertEquals(HomeModel.Counting(8), awaitItem())

            counter.send(HomeEvent.Add)
            assertEquals(HomeModel.Counting(9), awaitItem())

            counter.send(HomeEvent.Add)
            assertEquals(HomeModel.Max, awaitItem())
        }
    }
}