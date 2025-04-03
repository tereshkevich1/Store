package com.example.store.feature.common.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.store.feature.common.presentation.navigation.AppNavGraph
import com.example.store.feature.common.presentation.navigation.AuthCheckViewModel
import com.example.store.feature.common.presentation.navigation.bottom_nav_bar.NavBar
import com.example.store.feature.common.presentation.navigation.bottom_nav_bar.topLevelRoutes
import com.example.store.feature.common.presentation.navigation.destinations.AuthDestination
import com.example.store.feature.common.presentation.navigation.destinations.PacksDestination
import com.example.store.ui.theme.StoreTheme

@Composable
fun StoreApp(authCheckViewModel: AuthCheckViewModel = hiltViewModel()) {
    val isUserAuthenticated = authCheckViewModel.isLoggedIn

    val startDestination = if (isUserAuthenticated) {
        PacksDestination.PacksNav
    } else {
        AuthDestination.AuthNav
    }

    val navController = rememberNavController()
    var bottomBarState by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    LaunchedEffect(currentDestination) {
        if (currentDestination != null) {
            bottomBarState = topLevelRoutes.any {
                currentDestination.hasRoute(
                    it.route::class
                )
            }
        }
    }

    StoreTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                NavBar(
                    navController = navController,
                    currentDestination = currentDestination,
                    bottomBarState = bottomBarState
                )
            }
        ) { innerPadding ->
            AppNavGraph(
                navController = navController,
                startDestination = startDestination,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

