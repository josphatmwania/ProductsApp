package com.josphat.productsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.josphat.productsapp.presentation.screens.ProductsScreen
import com.josphat.productsapp.presentation.screens.ProductDetailsScreen
import com.josphat.productsapp.presentation.viewmodel.ProductsViewModel

@Composable
fun AppNavigation(navController: NavHostController, viewModel: ProductsViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.ProductsScreen.route
    ) {
        composable(Screens.ProductsScreen.route) {
            ProductsScreen(viewModel = viewModel, navController = navController)
        }
        composable(Screens.ProductDetailsScreen.route + "/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toInt() ?: 0
            ProductDetailsScreen(
                productId = productId,
                viewModel = viewModel,
                navController = navController )

        }
    }
}
