package com.example.store.feature.auth.presentation.sign_up

import com.example.store.core.presentation.UiText

sealed interface SignUpScreenEvent {
    data object NavigateToPackList : SignUpScreenEvent
    data object NavigateToSignInScreen : SignUpScreenEvent
    data class ShowToast(val message: UiText) : SignUpScreenEvent
}