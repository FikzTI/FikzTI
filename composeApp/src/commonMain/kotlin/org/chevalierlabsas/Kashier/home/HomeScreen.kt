package org.chevalierlabsas.Kashier.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (String) -> Unit,
    onNavigate: () -> Unit
) {
    Text(text = "Ini Halaman Home")
}