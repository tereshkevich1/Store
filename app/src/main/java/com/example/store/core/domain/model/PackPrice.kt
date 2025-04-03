package com.example.store.core.domain.model

data class PackPrice(
    val id: Int,
    val packId: Int,
    val price: Int,
    val bonus: Int
)