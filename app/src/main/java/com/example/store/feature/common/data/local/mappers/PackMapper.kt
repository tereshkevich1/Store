package com.example.store.feature.common.data.local.mappers

import com.example.store.feature.common.data.local.entity.PackEntity
import com.example.store.feature.common.domain.model.Pack

fun PackEntity.toPack() = Pack(
    id = id,
    unitId = unitId,
    name = name,
    type = type,
    quant = quant
)

fun PackEntity.toEntity() = PackEntity(
    id = id,
    unitId = unitId,
    name = name,
    type = type,
    quant = quant
)