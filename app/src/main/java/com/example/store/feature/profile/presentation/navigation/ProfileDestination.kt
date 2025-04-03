package com.example.store.feature.profile.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.common.presentation.navigation.destinations.ProfileDestination
import com.example.store.feature.profile.presentation.profile_screen.ProfileScreenRoute

fun NavGraphBuilder.profileDestination(navController: NavController) {
    composable<ProfileDestination.ProfileScreen> {
        ProfileScreenRoute(onDetailsScreenNavigate = { purchaseId ->
            navController.navigate(
                ProfileDestination.PurchaseDetailsScreen(
                    purchaseId = purchaseId
                )
            )
        })
    }
}