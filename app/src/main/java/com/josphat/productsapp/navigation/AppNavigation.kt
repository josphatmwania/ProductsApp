package com.josphat.productsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.ProductScreen)
} {
    composable(Screens.ProductScreen.route) {
        ProductScreen()
    }
}