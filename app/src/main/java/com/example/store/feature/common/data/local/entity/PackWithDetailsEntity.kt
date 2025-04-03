package com.example.store.feature.common.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PackWithDetailsEntity(
    @Embedded
    val pack: PackEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "pack_id"
    )
    val packPrice: PackPriceEntity,
    @Relation(
        parentColumn = "unit_id",
        entityColumn = "id"
    )
    val unit: UnitEntity
)