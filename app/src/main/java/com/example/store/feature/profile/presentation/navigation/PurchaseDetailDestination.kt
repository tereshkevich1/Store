package com.example.store.feature.profile.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.store.feature.common.presentation.navigation.destinations.ProfileDestination
import com.example.store.feature.profile.presentation.details_screen.PurchaseDetailsRoute

fun NavGraphBuilder.purchaseDetailDestination(navController: NavController) {
    composable<ProfileDestination.PurchaseDetailsScreen> {
        val args = it.toRoute<ProfileDestination.PurchaseDetailsScreen>()
        PurchaseDetailsRoute(purchaseId = args.purchaseId)
    }
}