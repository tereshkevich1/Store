package com.example.store.core.data.remote.model

data class Purchase(
    val items: List<PackFirestore> = emptyList(),
    val totalPrice: Int = 0,
    val timestamp: Long = System.currentTimeMillis()
)