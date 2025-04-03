package com.example.store.core.data.remote.mappers

import com.example.store.core.data.remote.model.PackFirestore
import com.example.store.feature.store.presentation.models.CartPack

fun CartPack.toPackFirestore() = PackFirestore(
    packId = packWithDetailsUi.id,
    price = packWithDetailsUi.discountedPrice.value,
    quantity = quantity.value,
)