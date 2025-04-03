package com.example.store.feature.profile.presentation.details_screen

import com.example.store.core.presentation.UiText

sealed interface DetailsScreeEvent {
    data class ShowToast(val message: UiText): DetailsScreeEvent
}