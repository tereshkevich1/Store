package com.example.store.feature.common.presentation.navigation.routes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.store.feature.common.presentation.navigation.destinations.PacksDestination
import com.example.store.feature.store.presentation.navigation.packsListDestination

fun NavGraphBuilder.addPacksRoute(navController: NavController) {
    navigation<PacksDestination.PacksNav>(
        startDestination = PacksDestination.PacksScreen
    ) {
        packsListDestination(navController)
    }
}




