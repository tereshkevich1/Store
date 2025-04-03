package com.example.store.core.presentation.errors_ui

import com.example.store.R
import com.example.store.core.data.remote.errors.FirestoreError
import com.example.store.core.presentation.util.UiText

fun FirestoreError.asUiText(): UiText {
    return when (this) {
        FirestoreError.UNKNOWN_ERROR -> UiText.StringResource(R.string.unknown_error)
    }
}