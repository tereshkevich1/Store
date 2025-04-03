package com.example.store.feature.store.domain.repository

import com.example.store.core.data.remote.errors.FirestoreError
import com.example.store.core.data.remote.model.Purchase
import com.example.store.core.domain.Result
import com.example.store.feature.store.presentation.models.CartPack
import com.example.store.feature.store.presentation.models.PackWithDetailsUi
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartState(): Flow<List<CartPack>>
    suspend fun addPackToCart(pack: PackWithDetailsUi, quantity: Int)
    suspend fun checkoutCart(
        purchase: Purchase
    ): Result<Unit, FirestoreError>
}