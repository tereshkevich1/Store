package com.example.store.feature.store.domain.use_case

import com.example.store.feature.store.presentation.models.PackType
import javax.inject.Inject

class CalculateItemTotalPriceUseCase @Inject constructor() {
    operator fun invoke(quantity: Int, discountedPrice: Int, type: PackType): Int {
        return when (type) {
            PackType.UNIT -> discountedPrice * quantity
            PackType.WEIGHT -> discountedPrice * quantity / 1000
        }
    }
}