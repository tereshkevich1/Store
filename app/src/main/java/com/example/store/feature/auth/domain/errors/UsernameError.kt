package com.example.store.feature.auth.domain.errors

import com.example.store.core.domain.Error

enum class UsernameError : Error {
    FIELD_EMPTY,
    INPUT_TOO_SHORT
}