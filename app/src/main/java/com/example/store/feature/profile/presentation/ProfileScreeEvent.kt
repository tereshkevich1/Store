package com.example.store.feature.profile.presentation

import com.example.store.core.presentation.util.UiText

sealed interface ProfileScreeEvent {
    data object NavigateToDetailsScreen : ProfileScreeEvent
    data class ShowToast(val message: UiText): ProfileScreeEvent
}