package com.example.store.feature.auth.presentation.errors_ui

import com.example.store.R
import com.example.store.core.presentation.util.UiText
import com.example.store.feature.auth.domain.errors.EmailError

fun EmailError.asUiText(): UiText {
    return when (this) {
        EmailError.FIELD_EMPTY -> UiText.StringResource(R.string.field_empty)
        EmailError.INVALID_EMAIL -> UiText.StringResource(R.string.invalid_email)
    }
}