package com.example.store.feature.auth.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.core.presentation.navigateSingleTop
import com.example.store.feature.auth.presentation.sign_in.SignInRoute
import com.example.store.feature.common.presentation.navigation.destinations.AuthDestination
import com.example.store.feature.common.presentation.navigation.destinations.PacksDestination

fun NavGraphBuilder.signInDestination(navController: NavController) {
    composable<AuthDestination.SignInScreen> {
        SignInRoute(
            onNavigateToSignUp = {
                navController.navigateSingleTop(AuthDestination.SignUpScreen)
            },
            onNavigateToPackList = {
                navController.navigate(PacksDestination.PacksScreen) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            }
        )
    }
}

