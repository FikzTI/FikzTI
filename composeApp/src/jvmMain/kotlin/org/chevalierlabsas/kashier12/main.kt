package org.chevalierlabsas.kashier12

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.chevalierlabsas.Kashier.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kashier",
    ) {
        App()
    }
}