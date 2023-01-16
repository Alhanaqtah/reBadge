package com.example.rebadge.navigation

sealed class Screen(val route: String) {
    object Send: Screen("send_screen")
    object Settings: Screen("settings_screen")
}
