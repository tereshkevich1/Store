package com.example.store.feature.auth.presentation.errors_ui

import com.example.store.R
import com.example.store.core.presentation.util.UiText
import com.example.store.feature.auth.domain.errors.FirebaseAuthError

fun FirebaseAuthError.asUiText(): UiText {
    return when (this) {
        FirebaseAuthError.INVALID_EMAIL -> UiText.StringResource(R.string.invalid_email)
        FirebaseAuthError.WRONG_PASSWORD -> UiText.StringResource(R.string.wrong_password)
        FirebaseAuthError.USER_NOT_FOUND -> UiText.StringResource(R.string.user_not_found)
        FirebaseAuthError.EMAIL_ALREADY_IN_USE -> UiText.StringResource(R.string.email_already_in_use)
        FirebaseAuthError.WEAK_PASSWORD -> UiText.StringResource(R.string.weak_password)
        FirebaseAuthError.NETWORK_ERROR -> UiText.StringResource(R.string.network_error)
        FirebaseAuthError.UNKNOWN_ERROR -> UiText.StringResource(R.string.unknown_error)
    }
}