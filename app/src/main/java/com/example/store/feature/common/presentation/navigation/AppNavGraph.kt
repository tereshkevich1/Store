package com.example.store.feature.common.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.store.feature.common.presentation.navigation.destinations.Destination
import com.example.store.feature.common.presentation.navigation.routes.addAuthRoute
import com.example.store.feature.common.presentation.navigation.routes.addCartRoute
import com.example.store.feature.common.presentation.navigation.routes.addPacksRoute
import com.example.store.feature.common.presentation.navigation.routes.addProfileRoute
import com.example.store.feature.common.presentation.navigation.util.enterFadeTransition
import com.example.store.feature.common.presentation.navigation.util.exitFadeTransition
import com.example.store.feature.common.presentation.navigation.util.popEnterFadeTransition
import com.example.store.feature.common.presentation.navigation.util.popExitFadeTransition

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { enterFadeTransition() },
        exitTransition = { exitFadeTransition() },
        popEnterTransition = { popEnterFadeTransition() },
        popExitTransition = { popExitFadeTransition() },
        modifier = modifier
    ) {
        addPacksRoute(navController)
        addCartRoute(navController)
        addProfileRoute(navController)
        addAuthRoute(navController)
    }
}

