package com.example.rebadge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rebadge.screens.send.SendScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Send.route
    ) {
        composable(Screen.Send.route) { SendScreen() }
        composable(Screen.Settings.route) {  }
    }
}