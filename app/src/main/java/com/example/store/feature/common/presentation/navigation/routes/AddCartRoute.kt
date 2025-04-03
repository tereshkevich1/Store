package com.example.store.feature.common.presentation.navigation.routes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.store.feature.common.presentation.navigation.destinations.CartDestination
import com.example.store.feature.store.presentation.navigation.cartDestination

fun NavGraphBuilder.addCartRoute(navController: NavController) {
    navigation<CartDestination.CartNav>(
        startDestination = CartDestination.CartScreen
    ) {
        cartDestination(navController)
    }
}


