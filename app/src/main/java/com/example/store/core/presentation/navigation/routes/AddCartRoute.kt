package com.example.store.core.presentation.navigation.routes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.store.core.presentation.navigation.destinations.CartDestination
import com.example.store.feature.store.presentation.cart_screen.CartScreenRoute

fun NavGraphBuilder.addCartRoute(navController: NavController) {
    navigation<CartDestination.CartNav>(
        startDestination = CartDestination.CartScreen
    ) {
        cartDestination(navController)
    }
}


fun NavGraphBuilder.cartDestination(navController: NavController) {
    composable<CartDestination.CartScreen> {
        CartScreenRoute()
    }
}


