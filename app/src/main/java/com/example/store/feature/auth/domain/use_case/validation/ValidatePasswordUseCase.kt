package com.example.store.feature.auth.domain.use_case.validation

import com.example.store.core.domain.Result
import com.example.store.feature.auth.domain.util.AuthConstants
import com.example.store.feature.auth.domain.errors.PasswordError
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {

    operator fun invoke(password: String): Result<Unit, PasswordError> {
        if (password.isBlank()) {
            return Result.Error(PasswordError.FIELD_EMPTY)
        }
        if (password.length < AuthConstants.MIN_PASSWORD_LENGTH) {
            return Result.Error(PasswordError.INPUT_TOO_SHORT)
        }
        if (!password.any { it.isDigit() } || !password.any { it.isLetter() }) {
            return Result.Error(PasswordError.INVALID_PASSWORD)
        }
        return Result.Success(Unit)
    }
}