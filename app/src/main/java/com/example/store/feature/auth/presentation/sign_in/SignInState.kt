package com.example.store.feature.auth.presentation.sign_in

import com.example.store.core.presentation.util.UiText

data class SignInState(
    val emailText: String = "",
    val emailError: UiText? = null,
    val passwordText: String = "",
    val passwordError: UiText? = null,
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false
)