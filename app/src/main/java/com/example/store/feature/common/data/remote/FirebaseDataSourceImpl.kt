package com.example.store.feature.common.data.remote

import com.example.store.feature.common.data.remote.model.Purchase
import com.example.store.feature.common.data.remote.model.PurchaseSummary
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.getField
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) :
    FirebaseDataSource {
    override suspend fun savePurchase(userId: String, purchase: Purchase) {
        firestore.collection(USERS_COLLECTION).document(userId).collection(PURCHASES_COLLECTION)
            .add(purchase).await()
    }

    override suspend fun saveUserToFirestore(userId: String, name: String) {
        val userMap = hashMapOf(NAME_FIELD to name)
        firestore.collection(USERS_COLLECTION).document(userId).set(userMap).await()
    }

    override suspend fun getUserName(userId: String): String {
        return firestore.collection(USERS_COLLECTION).document(userId).get().await()
            .getField<String>(NAME_FIELD).toString()
    }

    override suspend fun getPurchaseSummaries(userId: String): List<PurchaseSummary> {
        return firestore.collection(USERS_COLLECTION).document(userId).collection(
            PURCHASES_COLLECTION
        ).get().await().documents
            .mapNotNull { doc ->
                val totalPrice = doc.getLong(TOTAL_PRICE_FIELD)?.toInt() ?: return@mapNotNull null
                val timestamp = doc.getLong(TIMESTAMP_FIELD) ?: return@mapNotNull null
                val id = doc.id
                PurchaseSummary(id, totalPrice, timestamp)
            }
    }

    override suspend fun getPurchase(
        userId: String,
        purchaseId: String
    ): Purchase? {
        val documentSnapshot = firestore.collection(USERS_COLLECTION)
            .document(userId)
            .collection(PURCHASES_COLLECTION)
            .document(purchaseId)
            .get()
            .await()

        return documentSnapshot.toObject(Purchase::class.java)
    }

    companion object {
        const val USERS_COLLECTION = "users"
        const val PURCHASES_COLLECTION = "purchases"
        const val NAME_FIELD = "name"
        const val TOTAL_PRICE_FIELD = "totalPrice"
        const val TIMESTAMP_FIELD = "timestamp"
    }
}