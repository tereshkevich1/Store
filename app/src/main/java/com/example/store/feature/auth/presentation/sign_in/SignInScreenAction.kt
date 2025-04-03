package com.example.store.feature.auth.presentation.sign_in

sealed interface SignInScreenAction {
    data class OnEnterEmail(val value: String) : SignInScreenAction
    data class OnEnterPassword(val value: String) : SignInScreenAction
    data object OnTogglePasswordVisibility : SignInScreenAction
    data object OnSignIn : SignInScreenAction
    data object OnSignUp : SignInScreenAction
}