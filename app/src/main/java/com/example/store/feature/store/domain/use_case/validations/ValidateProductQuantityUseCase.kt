package com.example.store.feature.store.domain.use_case.validations

import com.example.store.core.domain.Result
import com.example.store.core.domain.Result.Success
import com.example.store.feature.store.domain.use_case.validations.errors.ProductQuantityError
import javax.inject.Inject

class ValidateProductQuantityUseCase @Inject constructor() {
    operator fun invoke(newValue: String, maxValue: Int): Result<Int?, ProductQuantityError> {
        if (newValue.isBlank()) return Success(null)
        val num = newValue.toIntOrNull() ?: return Result.Error(ProductQuantityError.INVALID_NUMBER)
        return when {
            num in 1..maxValue -> Success(num)
            num > maxValue -> Result.Error(ProductQuantityError.EXCEEDS_MAX)
            else -> Result.Error(ProductQuantityError.TOO_LOW)
        }
    }
}
