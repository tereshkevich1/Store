package com.example.store.feature.common.presentation.navigation.routes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.store.feature.common.presentation.navigation.destinations.AuthDestination
import com.example.store.feature.auth.presentation.navigation.signInDestination
import com.example.store.feature.auth.presentation.navigation.signUpDestination

fun NavGraphBuilder.addAuthRoute(navController: NavController) {
    navigation<AuthDestination.AuthNav>(
        startDestination = AuthDestination.SignUpScreen
    ) {
        signInDestination(navController)
        signUpDestination(navController)
    }
}

