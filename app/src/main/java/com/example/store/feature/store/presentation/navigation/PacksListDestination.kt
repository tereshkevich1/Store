package com.example.store.feature.store.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.common.presentation.navigation.destinations.PacksDestination
import com.example.store.feature.store.presentation.packs_list_screen.PacksListRoute

fun NavGraphBuilder.packsListDestination(navController: NavController) {
    composable<PacksDestination.PacksScreen> {
        PacksListRoute()
    }
}