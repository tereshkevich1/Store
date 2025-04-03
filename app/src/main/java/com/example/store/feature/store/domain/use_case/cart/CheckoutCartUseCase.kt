package com.example.store.feature.store.domain.use_case.cart

import com.example.store.feature.common.data.remote.errors.FirestoreError
import com.example.store.feature.common.data.remote.mappers.toPackFirestore
import com.example.store.feature.common.data.remote.model.Purchase
import com.example.store.core.domain.Result
import com.example.store.feature.store.domain.repository.CartRepository
import com.example.store.feature.store.presentation.models.CartPack
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CheckoutCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    private val getCartTotalPriceUseCase: GetCartTotalPriceUseCase
) {
    suspend operator fun invoke(packList: List<CartPack>): Result<Unit, FirestoreError> =
        withContext(Dispatchers.IO) {
            val totalPrice = getCartTotalPriceUseCase(packList).value
            val items = packList.map { it.toPackFirestore() }
            cartRepository.checkoutCart(Purchase(items, totalPrice))
        }
}