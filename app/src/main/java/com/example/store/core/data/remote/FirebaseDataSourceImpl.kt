package com.example.store.core.data.remote

import com.example.store.core.data.remote.model.Purchase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.getField
import com.google.firebase.firestore.toObjects
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

    override suspend fun getUserPurchases(userId: String): List<Purchase> {
        return firestore.collection(USERS_COLLECTION).document(userId).collection(
            PURCHASES_COLLECTION
        ).get().await().toObjects()
    }

    companion object {
        const val USERS_COLLECTION = "users"
        const val PURCHASES_COLLECTION = "purchases"
        const val NAME_FIELD = "name"
    }
}