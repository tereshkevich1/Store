package com.example.store.feature.store.data.repository

import com.example.store.core.data.remote.FirebaseDataSource
import com.example.store.core.data.remote.errors.FirestoreError
import com.example.store.core.data.remote.model.Purchase
import com.example.store.core.domain.Result
import com.example.store.feature.store.domain.repository.CartRepository
import com.example.store.feature.store.presentation.models.CartPack
import com.example.store.feature.store.presentation.models.PackWithDetailsUi
import com.example.store.feature.store.presentation.models.toFormattedQuantity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

// The task did not specify that cart data should be persisted, so it is temporarily stored
// in this makeshift repository using a UI model.

class CartRepositoryImpl @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource,
    firebaseAuth: FirebaseAuth
) : CartRepository {
    private val cartListFlow = MutableStateFlow<List<CartPack>>(
        mutableListOf()
    )

    private val userId = firebaseAuth.uid ?: ""

    override fun getCartState(): Flow<List<CartPack>> {
        return cartListFlow
    }

    override suspend fun addPackToCart(pack: PackWithDetailsUi, quantity: Int) {
        cartListFlow.update { it.updateOrAddItem(pack, quantity) }
    }

    override suspend fun checkoutCart(
        purchase: Purchase
    ): Result<Unit, FirestoreError> {
        return try {
            firebaseDataSource.savePurchase(userId, purchase)
            cartListFlow.update { emptyList() }

            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(FirestoreError.UNKNOWN_ERROR)
        }
    }
}

private fun List<CartPack>.updateOrAddItem(
    item: PackWithDetailsUi,
    quantity: Int
): List<CartPack> {
    val existingItemIndex = indexOfFirst { it.packWithDetailsUi == item }
    return if (existingItemIndex != -1) {
        toMutableList().apply {
            val existingItem = get(existingItemIndex)
            set(
                existingItemIndex,
                existingItem.copy(
                    quantity = (existingItem.quantity.value + quantity).toFormattedQuantity(
                        existingItem.packWithDetailsUi.type
                    )
                )
            )
        }
    } else {
        this + CartPack(
            packWithDetailsUi = item,
            quantity = quantity.toFormattedQuantity(item.type)
        )
    }
}