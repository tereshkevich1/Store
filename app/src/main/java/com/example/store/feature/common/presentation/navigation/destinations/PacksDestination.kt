package com.example.store.feature.common.presentation.navigation.destinations

import kotlinx.serialization.Serializable

sealed class PacksDestination : Destination {
    @Serializable
    data object PacksNav : PacksDestination()

    @Serializable
    data object PacksScreen: PacksDestination()
}