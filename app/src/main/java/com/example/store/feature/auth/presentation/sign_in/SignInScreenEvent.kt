package com.example.store.feature.auth.presentation.sign_in

import com.example.store.core.presentation.UiText

sealed interface SignInScreenEvent {
    data object NavigateToPackList : SignInScreenEvent
    data object NavigateToSignUp : SignInScreenEvent
    data class ShowToast(val message: UiText) : SignInScreenEvent
}