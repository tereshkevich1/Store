package com.example.store.feature.common.data.remote.mappers

import com.example.store.feature.common.data.remote.model.PackFirestore
import com.example.store.feature.store.presentation.models.CartPack

fun CartPack.toPackFirestore() = PackFirestore(
    name = packWithDetailsUi.name,
    packId = packWithDetailsUi.id,
    price = packWithDetailsUi.discountedPrice.value,
    quantity = quantity.value,
)