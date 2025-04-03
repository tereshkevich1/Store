package com.example.store.core.data.local.mappers

import com.example.store.core.data.local.entity.PackPriceEntity
import com.example.store.core.domain.model.PackPrice

fun PackPriceEntity.toPackPrice() = PackPrice(
    id = id,
    packId = packId,
    price = price,
    bonus = bonus
)

fun PackPrice.toEntity() = PackPriceEntity(
    id = id,
    packId = packId,
    price = price,
    bonus = bonus
)