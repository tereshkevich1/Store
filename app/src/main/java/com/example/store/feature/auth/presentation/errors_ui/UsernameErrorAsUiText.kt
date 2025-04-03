package com.example.store.feature.auth.presentation.errors_ui

import com.example.store.R
import com.example.store.core.presentation.util.UiText
import com.example.store.feature.auth.domain.errors.UsernameError

fun UsernameError.asUiText(): UiText {
    return when (this) {
        UsernameError.FIELD_EMPTY -> UiText.StringResource(R.string.field_empty)
        UsernameError.INPUT_TOO_SHORT -> UiText.StringResource(R.string.input_too_short)
    }
}