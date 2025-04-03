package com.example.store.feature.profile.presentation

sealed interface ProfileScreenAction {
    data object OnPurchaseHistoryItemClick : ProfileScreenAction
}