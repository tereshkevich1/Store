package com.example.store.core.data.remote

import com.example.store.core.data.remote.model.Purchase

interface FirebaseDataSource {
    suspend fun savePurchase(userId: String, purchase: Purchase)
    suspend fun saveUserToFirestore(userId: String, name: String)
    suspend fun getUserName(userId: String): String
    suspend fun getUserPurchases(userId: String): List<Purchase>
}

