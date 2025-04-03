package com.example.store.feature.profile.presentation.model

import com.example.store.feature.common.data.remote.model.PackFirestore
import com.example.store.feature.store.presentation.models.DisplayableNumber
import com.example.store.feature.store.presentation.models.toFormattedPrice

data class PackFirestoreUi(
    val name: String,
    val packId: Int,
    val price: DisplayableNumber,
    val quantity: String,
)

fun PackFirestore.toPackFirestoreUi() = PackFirestoreUi(
    name = name,
    packId = packId,
    price = price.toFormattedPrice(),
    quantity = quantity.toString()
)