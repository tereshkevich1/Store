package com.example.store.feature.store.domain.use_case.cart

import com.example.store.feature.store.domain.repository.CartRepository
import com.example.store.feature.store.presentation.models.PackWithDetailsUi
import javax.inject.Inject

class AddPackToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(pack: PackWithDetailsUi, quantity: Int) {
        cartRepository.addPackToCart(pack, quantity)
    }
}