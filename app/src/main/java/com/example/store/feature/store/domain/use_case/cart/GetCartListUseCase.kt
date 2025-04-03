package com.example.store.feature.store.domain.use_case.cart

import com.example.store.feature.store.domain.repository.CartRepository
import com.example.store.feature.store.presentation.models.CartPack
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartListUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    operator fun invoke(): Flow<List<CartPack>> = cartRepository.getCartState()
}

