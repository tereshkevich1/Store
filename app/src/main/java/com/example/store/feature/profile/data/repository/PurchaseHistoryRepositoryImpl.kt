package com.example.store.feature.profile.data.repository

import com.example.store.core.data.remote.FirebaseDataSource
import com.example.store.core.data.remote.errors.FirestoreError
import com.example.store.core.data.remote.model.Purchase
import com.example.store.core.domain.Result
import com.example.store.feature.profile.domain.repository.PurchaseHistoryRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class PurchaseHistoryRepositoryImpl @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource,
    firebaseAuth: FirebaseAuth
) : PurchaseHistoryRepository {

    private val userId = firebaseAuth.uid ?: ""

    override suspend fun getUserPurchases(): Result<List<Purchase>, FirestoreError> =
        try {
            val purchases = firebaseDataSource.getUserPurchases(userId)
            Result.Success(purchases)
        } catch (e: Exception) {
            Result.Error(FirestoreError.UNKNOWN_ERROR)
        }

    override suspend fun getUserName(): Result<String, FirestoreError> =
        try {
            val userName = firebaseDataSource.getUserName(userId)
            Result.Success(userName)
        } catch (e: Exception) {
            Result.Error(FirestoreError.UNKNOWN_ERROR)
        }

}