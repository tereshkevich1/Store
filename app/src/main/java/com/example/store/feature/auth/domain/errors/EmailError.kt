package com.example.store.feature.auth.domain.errors

import com.example.store.core.domain.Error

enum class EmailError : Error {
    FIELD_EMPTY,
    INVALID_EMAIL
}