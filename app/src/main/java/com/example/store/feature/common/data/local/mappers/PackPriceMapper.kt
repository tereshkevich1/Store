package com.example.store.feature.common.data.local.mappers

import com.example.store.feature.common.data.local.entity.PackPriceEntity
import com.example.store.feature.common.domain.model.PackPrice

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