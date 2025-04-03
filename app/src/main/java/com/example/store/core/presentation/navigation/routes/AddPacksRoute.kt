package com.example.store.core.presentation.navigation.routes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.store.core.presentation.navigation.destinations.PacksDestination
import com.example.store.feature.store.presentation.packs_list_screen.PacksListRoute

fun NavGraphBuilder.addPacksRoute(navController: NavController) {
    navigation<PacksDestination.PacksNav>(
        startDestination = PacksDestination.PacksScreen
    ) {
        packsListDestination(navController)
    }
}

fun NavGraphBuilder.packsListDestination(navController: NavController) {
    composable<PacksDestination.PacksScreen> {
        PacksListRoute()
    }
}




