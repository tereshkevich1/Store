package com.example.store.feature.store.presentation.models

data class CartPack(
    val packWithDetailsUi: PackWithDetailsUi,
    val quantity: DisplayableNumber,
    val totalPrice: DisplayableNumber
)