package com.example.store.core.presentation.navigation.destinations

import kotlinx.serialization.Serializable

sealed class ProfileDestination : Destination {
    @Serializable
    data object ProfileNav : ProfileDestination()

    @Serializable
    data object ProfileScreen: ProfileDestination()
}