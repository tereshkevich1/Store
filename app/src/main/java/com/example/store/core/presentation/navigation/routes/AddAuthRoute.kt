package com.example.store.core.presentation.navigation.routes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.store.core.presentation.navigation.destinations.AuthDestination
import com.example.store.core.presentation.navigation.destinations.PacksDestination
import com.example.store.feature.auth.presentation.sign_in.SignInRoute
import com.example.store.feature.auth.presentation.sign_up.SignUpRoute

fun NavGraphBuilder.addAuthRoute(navController: NavController) {
    navigation<AuthDestination.AuthNav>(
        startDestination = AuthDestination.SignUpScreen
    ) {
        signInDestination(navController)
        signUpDestination(navController)
    }
}

fun NavGraphBuilder.signInDestination(navController: NavController) {
    composable<AuthDestination.SignInScreen> {
        SignInRoute(
            onNavigateToSignUp = { navController.navigate(AuthDestination.SignUpScreen){

            } },
            onNavigateToPackList = { navController.navigate(PacksDestination.PacksScreen) }
        )
    }
}

fun NavGraphBuilder.signUpDestination(navController: NavController) {
    composable<AuthDestination.SignUpScreen> {
        SignUpRoute(
            onNavigateToSignIn = { navController.navigate(AuthDestination.SignInScreen) },
            onNavigateToPackList = { navController.navigate(PacksDestination.PacksScreen) }
        )
    }
}