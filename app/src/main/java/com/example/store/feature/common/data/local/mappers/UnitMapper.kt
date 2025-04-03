package com.example.store.feature.common.data.local.mappers

import com.example.store.feature.common.data.local.entity.UnitEntity
import com.example.store.feature.common.domain.model.Unit

fun UnitEntity.toUnit() = Unit(
    id = id,
    name = name
)

fun Unit.toEntity() = UnitEntity(
    id = id,
    name = name
)