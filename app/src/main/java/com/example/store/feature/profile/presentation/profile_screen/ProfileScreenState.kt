package com.example.store.feature.profile.presentation.profile_screen

import com.example.store.feature.profile.presentation.model.PurchaseSummaryUi

data class ProfileScreenState(
    val purchaseList: List<PurchaseSummaryUi> = emptyList(),
    val username: String = "",
    val isLoading: Boolean = true
)