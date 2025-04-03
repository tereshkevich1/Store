package com.example.store.feature.store.presentation.cart_screen

import com.example.store.feature.store.presentation.models.CartPack
import com.example.store.feature.store.presentation.models.DisplayableNumber
import com.example.store.feature.store.presentation.models.PackType
import com.example.store.feature.store.presentation.models.toFormattedPrice

data class CartState(
    val packList: List<CartPack> = emptyList(),
    val totalPrice: DisplayableNumber? = null
)