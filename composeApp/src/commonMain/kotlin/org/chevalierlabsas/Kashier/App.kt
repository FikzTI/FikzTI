package org.chevalierlabsas.kashier


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.chevalierlabsas.Kashier.history.presentation.HistoryScreen
import org.chevalierlabsas.Kashier.home.HomeScreen
import org.chevalierlabsas.Kashier.home.HomeViewModel


@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
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

            composable(route = "history") {
                HistoryScreen(
                    onNavigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}