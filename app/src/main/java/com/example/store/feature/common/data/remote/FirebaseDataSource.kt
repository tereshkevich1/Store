package com.example.store.feature.common.data.remote

import com.example.store.feature.common.data.remote.model.Purchase
import com.example.store.feature.common.data.remote.model.PurchaseSummary

interface FirebaseDataSource {
    suspend fun savePurchase(userId: String, purchase: Purchase)
    suspend fun saveUserToFirestore(userId: String, name: String)
    suspend fun getUserName(userId: String): String
    suspend fun getPurchaseSummaries(userId: String): List<PurchaseSummary>
    suspend fun getPurchase(userId: String, purchaseId: String): Purchase?
}

