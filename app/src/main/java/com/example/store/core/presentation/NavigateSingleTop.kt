package com.example.store.core.presentation

import androidx.navigation.NavController
import com.example.store.feature.common.presentation.navigation.destinations.Destination

fun NavController.navigateSingleTop(route: Destination) {
    navigate(route) {
        launchSingleTop = true
        popUpTo(route) {
            inclusive = true
        }
    }
}