package com.example.store.feature.store.presentation.packs_list_screen.bottom_sheet

import com.example.store.R
import com.example.store.core.presentation.util.UiText
import com.example.store.feature.store.domain.use_case.validations.errors.ProductQuantityError

fun ProductQuantityError.asUiText(): UiText {
    return when (this) {
        ProductQuantityError.INVALID_FORMAT ->
            UiText.StringResource(R.string.error_invalid_format)
        ProductQuantityError.INVALID_NUMBER ->
            UiText.StringResource(R.string.error_invalid_number)
        ProductQuantityError.TOO_LOW ->
            UiText.StringResource(R.string.error_too_low)
        ProductQuantityError.EXCEEDS_MAX ->
            UiText.StringResource(R.string.error_exceeds_max)
    }
}