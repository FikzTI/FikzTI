package org.chevalierlabsas.Kashier

import HistoryScreen
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.chevalierlabsas.Kashier.history.presentation.HistoryViewModel
import org.chevalierlabsas.Kashier.home.HomeScreen
import org.chevalierlabsas.Kashier.home.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            // Rute Home
            composable(route = "home") {
                val vm: HomeViewModel = viewModel()
                val state by vm.state.collectAsState()

                HomeScreen(
                    state = state,
                    onEvent = vm::onEvent,
                    onNavigate = {
                        navController.navigate(route = "history")
                    }
                )
            }

            // Rute History (Hanya satu rute saja di sini)
            composable(route = "history") {
                val viewModel: HistoryViewModel = koinViewModel()
                val state by viewModel.state.collectAsState()

                HistoryScreen(
                    state = state,
                    onNavigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}