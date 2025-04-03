package com.example.store.core.presentation.navigation.destinations

import kotlinx.serialization.Serializable

sealed class CartDestination: Destination {
    @Serializable
    data object CartNav: CartDestination()

    @Serializable
    data object CartScreen: CartDestination()
}

