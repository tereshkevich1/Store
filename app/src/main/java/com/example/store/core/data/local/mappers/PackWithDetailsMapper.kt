package com.example.store.core.data.local.mappers

import com.example.store.core.data.local.entity.PackWithDetailsEntity
import com.example.store.core.domain.model.PackWithDetails

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