package com.example.store.feature.profile.domain.repository

import com.example.store.feature.common.data.remote.errors.FirestoreError
import com.example.store.feature.common.data.remote.model.Purchase
import com.example.store.feature.common.data.remote.model.PurchaseSummary
import com.example.store.core.domain.Result

interface PurchaseHistoryRepository {
    suspend fun getPurchaseSummaries(): Result<List<PurchaseSummary>, FirestoreError>
    suspend fun getPurchase(purchaseId: String): Result<Purchase, FirestoreError>
    suspend fun getUserName(): Result<String, FirestoreError>
}