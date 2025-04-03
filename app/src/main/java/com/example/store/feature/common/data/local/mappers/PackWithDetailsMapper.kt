package com.example.store.feature.common.data.local.mappers

import com.example.store.feature.common.data.local.entity.PackWithDetailsEntity
import com.example.store.feature.common.domain.model.PackWithDetails

fun PackWithDetailsEntity.toPackWithDetails() = PackWithDetails(
    pack = pack,
    packPrice = packPrice,
    unit = unit
)

fun PackWithDetails.toEntity() = PackWithDetailsEntity(
    pack = pack,
    packPrice = packPrice,
    unit = unit
)