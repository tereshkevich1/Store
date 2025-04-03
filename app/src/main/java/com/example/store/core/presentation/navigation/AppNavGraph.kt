package com.example.store.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.store.core.presentation.navigation.destinations.Destination
import com.example.store.core.presentation.navigation.routes.addAuthRoute
import com.example.store.core.presentation.navigation.routes.addCartRoute
import com.example.store.core.presentation.navigation.routes.addPacksRoute
import com.example.store.core.presentation.navigation.routes.addProfileRoute
import com.example.store.core.presentation.navigation.util.enterSlideTransition
import com.example.store.core.presentation.navigation.util.exitSlideTransition
import com.example.store.core.presentation.navigation.util.popEnterSlideTransition
import com.example.store.core.presentation.navigation.util.popExitSlideTransition

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { enterSlideTransition() },
        exitTransition = { exitSlideTransition() },
        popEnterTransition = { popEnterSlideTransition() },
        popExitTransition = { popExitSlideTransition() },
        modifier = modifier
    ) {
        addPacksRoute(navController)
        addCartRoute(navController)
        addProfileRoute(navController)
        addAuthRoute(navController)
    }
}

