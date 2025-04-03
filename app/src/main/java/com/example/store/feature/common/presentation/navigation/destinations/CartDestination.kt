package com.example.store.feature.common.presentation.navigation.destinations

import kotlinx.serialization.Serializable

sealed class CartDestination: Destination {
    @Serializable
    data object CartNav: CartDestination()

    @Serializable
    data object CartScreen: CartDestination()
}

