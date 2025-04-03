package com.example.store.feature.profile.presentation.profile_screen

sealed interface ProfileScreenAction {
    data class OnPurchaseHistoryItemClick(val purchaseId: String) : ProfileScreenAction
}