package com.josphat.productsapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.josphat.productsapp.presentation.screens.ProductsScreen
import com.josphat.productsapp.presentation.screens.ProductDetailsScreen
import com.josphat.productsapp.presentation.viewmodel.ProductsViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: ProductsViewModel = viewModel() // Create the ViewModel once

    NavHost(
        navController = navController,
        startDestination = Screens.ProductScreen.route
    ) {
        composable(Screens.ProductScreen.route) {
            ProductsScreen(viewModel = viewModel) // Pass the ViewModel to ProductsScreen
        }
        composable(Screens.ProductDetailsScreen.route + "/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toInt() ?: 0
            ProductDetailsScreen(productId = productId, viewModel = viewModel) // Pass the ViewModel to ProductDetailsScreen
        }
    }
}
