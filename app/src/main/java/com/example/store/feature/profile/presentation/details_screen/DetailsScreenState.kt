package com.example.store.feature.profile.presentation.details_screen

import com.example.store.feature.profile.presentation.model.PackFirestoreUi

data class DetailsScreenState(
    val purchaseList: List<PackFirestoreUi> = emptyList(),
    val isLoading: Boolean = true
)