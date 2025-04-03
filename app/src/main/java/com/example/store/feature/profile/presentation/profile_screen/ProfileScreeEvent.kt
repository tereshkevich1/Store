package com.example.store.feature.profile.presentation.profile_screen

import com.example.store.core.presentation.UiText

sealed interface ProfileScreeEvent {
    data class NavigateToDetailsScreen(val purchaseId: String) : ProfileScreeEvent
    data class ShowToast(val message: UiText) : ProfileScreeEvent
}