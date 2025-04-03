package com.example.store.feature.profile.presentation

import com.example.store.feature.profile.presentation.model.PurchaseUi

data class ProfileScreenState(
    val purchaseList: List<PurchaseUi> = emptyList(),
    val username: String = "",
    val isLoading: Boolean = true
)