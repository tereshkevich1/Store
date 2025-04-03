package com.example.store.feature.auth.domain.errors

import com.example.store.core.domain.Error

enum class PasswordError : Error {
    FIELD_EMPTY,
    INVALID_PASSWORD,
    INPUT_TOO_SHORT
}