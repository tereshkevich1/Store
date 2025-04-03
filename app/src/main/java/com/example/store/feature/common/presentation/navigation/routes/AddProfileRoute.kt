package com.example.store.feature.common.presentation.navigation.routes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.store.feature.common.presentation.navigation.destinations.ProfileDestination
import com.example.store.feature.profile.presentation.navigation.profileDestination
import com.example.store.feature.profile.presentation.navigation.purchaseDetailDestination

fun NavGraphBuilder.addProfileRoute(navController: NavController) {
    navigation<ProfileDestination.ProfileNav>(
        startDestination = ProfileDestination.ProfileScreen
    ) {
        profileDestination(navController)
        purchaseDetailDestination(navController)
    }
}

