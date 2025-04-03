package com.example.store.core.presentation.navigation.routes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.store.core.presentation.navigation.destinations.ProfileDestination
import com.example.store.feature.profile.presentation.ProfileScreenRoute

fun NavGraphBuilder.addProfileRoute(navController: NavController) {
    navigation<ProfileDestination.ProfileNav>(
        startDestination = ProfileDestination.ProfileScreen
    ) {
        profileDestination(navController)
    }
}

fun NavGraphBuilder.profileDestination(navController: NavController) {
    composable<ProfileDestination.ProfileScreen> {
        ProfileScreenRoute(onDetailsScreenNavigate = {})
    }
}