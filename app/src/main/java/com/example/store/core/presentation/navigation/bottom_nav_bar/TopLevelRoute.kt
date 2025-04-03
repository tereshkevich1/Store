package com.example.store.core.presentation.navigation.bottom_nav_bar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.store.R
import com.example.store.core.presentation.navigation.destinations.CartDestination
import com.example.store.core.presentation.navigation.destinations.Destination
import com.example.store.core.presentation.navigation.destinations.PacksDestination
import com.example.store.core.presentation.navigation.destinations.ProfileDestination

data class TopLevelRoute(
    @StringRes val nameId: Int,
    val route: Destination,
    @DrawableRes val iconId: Int
)

val topLevelRoutes = listOf(
    TopLevelRoute(R.string.store, PacksDestination.PacksScreen, R.drawable.store),
    TopLevelRoute(R.string.cart, CartDestination.CartScreen, R.drawable.cart),
    TopLevelRoute(R.string.profile, ProfileDestination.ProfileScreen, R.drawable.person)
)

