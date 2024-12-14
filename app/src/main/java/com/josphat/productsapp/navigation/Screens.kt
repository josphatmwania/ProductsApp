package com.josphat.productsapp.navigation

sealed class Screens(val route: String) {
    object ProductsScreen: Screens("productsScreen")
    object ProductDetailsScreen: Screens("productsDetails")
}
