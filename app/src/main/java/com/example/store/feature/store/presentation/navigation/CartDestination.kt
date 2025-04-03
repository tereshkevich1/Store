package com.example.store.feature.store.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.common.presentation.navigation.destinations.CartDestination
import com.example.store.feature.store.presentation.cart_screen.CartScreenRoute

fun NavGraphBuilder.cartDestination(navController: NavController) {
    composable<CartDestination.CartScreen> {
        CartScreenRoute()
    }
}