package com.example.store.feature.auth.presentation.sign_up

import com.example.store.core.presentation.UiText

data class SignUpState(
    val usernameText: String = "",
    val usernameError: UiText? = null,
    val emailText: String = "",
    val emailError: UiText? = null,
    val passwordText: String = "",
    val passwordError: UiText? = null,
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false
)