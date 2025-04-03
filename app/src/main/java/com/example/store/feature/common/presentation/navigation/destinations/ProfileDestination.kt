package com.example.store.feature.common.presentation.navigation.destinations

import kotlinx.serialization.Serializable

sealed class ProfileDestination : Destination {
    @Serializable
    data object ProfileNav : ProfileDestination()

    @Serializable
    data object ProfileScreen: ProfileDestination()

    @Serializable
    data class PurchaseDetailsScreen(val purchaseId: String): ProfileDestination()
}