package com.example.store.core.data.local.entity

import androidx.room.Embedded

data class PackWithDetailsEntity(
    @Embedded(prefix = "pack_")
    val pack: PackEntity,
    @Embedded(prefix = "price_")
    val packPrice: PackPriceEntity,
    @Embedded(prefix = "unit_")
    val unit: UnitEntity
)