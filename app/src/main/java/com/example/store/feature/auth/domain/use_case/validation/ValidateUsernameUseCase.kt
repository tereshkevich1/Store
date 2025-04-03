package com.example.store.feature.auth.domain.use_case.validation

import com.example.store.core.domain.Result
import com.example.store.feature.auth.domain.util.AuthConstants
import com.example.store.feature.auth.domain.errors.UsernameError
import javax.inject.Inject

class ValidateUsernameUseCase @Inject constructor() {

    operator fun invoke(username: String): Result<Unit, UsernameError> {
        if (username.isBlank()) {
            return Result.Error(UsernameError.FIELD_EMPTY)
        }
        if (username.length < AuthConstants.MIN_USERNAME_LENGTH) {
            return Result.Error(UsernameError.INPUT_TOO_SHORT)
        }
        return Result.Success(Unit)
    }
}