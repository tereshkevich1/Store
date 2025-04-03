package com.example.store.feature.store.domain.use_case.cart

import com.example.store.feature.store.domain.use_case.CalculateItemTotalPriceUseCase
import com.example.store.feature.store.presentation.models.CartPack
import com.example.store.feature.store.presentation.models.DisplayableNumber
import com.example.store.feature.store.presentation.models.toFormattedPrice
import javax.inject.Inject

class GetCartTotalPriceUseCase @Inject constructor(
    private val calculateItemTotalPriceUseCase: CalculateItemTotalPriceUseCase
) {
    operator fun invoke(cartList: List<CartPack>): DisplayableNumber {
        val total = cartList.sumOf { cartPack ->
            calculateItemTotalPriceUseCase(
                quantity = cartPack.quantity.value,
                discountedPrice = cartPack.packWithDetailsUi.discountedPrice.value,
                type = cartPack.packWithDetailsUi.type
            ).toDouble()
        }.toInt()
        return total.toFormattedPrice()
    }
}