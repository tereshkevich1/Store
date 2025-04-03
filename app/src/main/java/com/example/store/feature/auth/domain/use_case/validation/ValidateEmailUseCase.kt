package com.example.store.feature.auth.domain.use_case.validation

import android.util.Patterns
import com.example.store.core.domain.Result
import com.example.store.feature.auth.domain.errors.EmailError
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {

    operator fun invoke(email: String): Result<Unit, EmailError> {
        if (email.isBlank()) {
            return Result.Error(EmailError.FIELD_EMPTY)
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Result.Error(EmailError.INVALID_EMAIL)
        }
        return Result.Success(Unit)
    }
}

