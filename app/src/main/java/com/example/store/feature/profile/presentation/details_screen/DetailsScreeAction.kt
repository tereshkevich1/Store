package com.example.store.feature.profile.presentation.details_screen

sealed interface DetailsScreeAction {
    data object OnPurchaseHistoryItemClick : DetailsScreeAction
}