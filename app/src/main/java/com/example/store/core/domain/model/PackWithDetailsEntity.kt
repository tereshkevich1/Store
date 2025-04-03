package com.example.store.core.domain.model

import com.example.store.core.data.local.entity.PackEntity
import com.example.store.core.data.local.entity.PackPriceEntity
import com.example.store.core.data.local.entity.UnitEntity

data class PackWithDetails(
    val pack: PackEntity,
    val packPrice: PackPriceEntity,
    val unit: UnitEntity
)

