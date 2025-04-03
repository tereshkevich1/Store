package com.example.store.feature.common.domain.model

import com.example.store.feature.common.data.local.entity.PackEntity
import com.example.store.feature.common.data.local.entity.PackPriceEntity
import com.example.store.feature.common.data.local.entity.UnitEntity

data class PackWithDetails(
    val pack: PackEntity,
    val packPrice: PackPriceEntity,
    val unit: UnitEntity
)

