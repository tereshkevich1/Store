package com.example.store.feature.common.presentation.navigation.destinations

import kotlinx.serialization.Serializable

sealed class AuthDestination: Destination {
    @Serializable
    data object AuthNav: AuthDestination()

    @Serializable
    data object SignInScreen: AuthDestination()

    @Serializable
    data object SignUpScreen: AuthDestination()
}