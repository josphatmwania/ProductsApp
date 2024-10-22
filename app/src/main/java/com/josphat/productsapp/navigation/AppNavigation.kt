package com.josphat.productsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.ProductScreen.route
    ) {
        composable(Screens.ProductScreen.route) {
            ProductScreen()
        }
        composable(Screens.ProductDetailsScreen.route + "/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toInt() ?: 0
            ProductDetailsScreen(productId = productId, viewModel = viewModel())
        }
    }
}
