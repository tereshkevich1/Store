package com.example.store.feature.auth.domain.errors

import com.example.store.core.domain.Error

enum class FirebaseAuthError: Error {
    INVALID_EMAIL,
    WRONG_PASSWORD,
    USER_NOT_FOUND,
    EMAIL_ALREADY_IN_USE,
    WEAK_PASSWORD,
    NETWORK_ERROR,
    UNKNOWN_ERROR
}