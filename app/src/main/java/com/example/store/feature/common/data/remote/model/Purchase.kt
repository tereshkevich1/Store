package com.example.store.feature.common.data.remote.model

data class Purchase(
    val items: List<PackFirestore> = emptyList(),
    val totalPrice: Int = 0,
    val timestamp: Long = System.currentTimeMillis()
)

data class PurchaseSummary(
    val id: String,
    val totalPrice: Int,
    val timestamp: Long
)