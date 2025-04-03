package com.example.store.feature.profile.domain.repository

import com.example.store.core.data.remote.errors.FirestoreError
import com.example.store.core.data.remote.model.Purchase
import com.example.store.core.domain.Result

interface PurchaseHistoryRepository {
    suspend fun getUserPurchases(): Result<List<Purchase>, FirestoreError>
    suspend fun getUserName(): Result<String, FirestoreError>
}