package com.example.store.feature.auth.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.core.presentation.navigateSingleTop
import com.example.store.feature.auth.presentation.sign_up.SignUpRoute
import com.example.store.feature.common.presentation.navigation.destinations.AuthDestination
import com.example.store.feature.common.presentation.navigation.destinations.PacksDestination

fun NavGraphBuilder.signUpDestination(navController: NavController) {
    composable<AuthDestination.SignUpScreen> {
        SignUpRoute(
            onNavigateToSignIn = {
                navController.navigateSingleTop(
                    AuthDestination.SignInScreen
                )
            },
            onNavigateToPackList = {
                navController.navigate(PacksDestination.PacksScreen) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            }
        )
    }
}