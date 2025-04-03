package com.example.store.feature.auth.presentation.errors_ui

import com.example.store.R
import com.example.store.core.presentation.util.UiText
import com.example.store.feature.auth.domain.errors.PasswordError

fun PasswordError.asUiText(): UiText {
    return when (this) {
        PasswordError.FIELD_EMPTY -> UiText.StringResource(R.string.field_empty)
        PasswordError.INVALID_PASSWORD -> UiText.StringResource(R.string.invalid_passwod)
        PasswordError.INPUT_TOO_SHORT -> UiText.StringResource(R.string.passwod_to_short)
    }
}