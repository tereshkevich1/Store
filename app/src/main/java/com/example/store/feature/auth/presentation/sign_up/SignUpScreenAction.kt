package com.example.store.feature.auth.presentation.sign_up

sealed interface SignUpScreenAction {
    data class OnEnterUsername(val value: String) : SignUpScreenAction
    data class OnEnterEmail(val value: String) : SignUpScreenAction
    data class OnEnterPassword(val value: String) : SignUpScreenAction
    data object OnTogglePasswordVisibility : SignUpScreenAction
    data object OnSignUp : SignUpScreenAction
    data object OnSignIn : SignUpScreenAction
}