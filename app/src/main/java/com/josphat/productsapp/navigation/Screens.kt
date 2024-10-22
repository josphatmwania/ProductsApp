package com.josphat.productsapp.navigation

sealed class Screens(val route: String) {
    object ProductScreen: Screens("products")
    object ProductDetailsScreen: Screens("productsDetails")
}
