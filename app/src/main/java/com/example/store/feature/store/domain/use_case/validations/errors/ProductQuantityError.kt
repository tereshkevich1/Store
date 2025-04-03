package com.example.store.feature.store.domain.use_case.validations.errors

import com.example.store.core.domain.Error

enum class ProductQuantityError : Error {
    INVALID_FORMAT,
    INVALID_NUMBER,
    TOO_LOW,
    EXCEEDS_MAX
}