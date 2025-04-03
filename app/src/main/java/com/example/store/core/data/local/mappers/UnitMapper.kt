package com.example.store.core.data.local.mappers

import com.example.store.core.data.local.entity.UnitEntity
import com.example.store.core.domain.model.Unit

fun UnitEntity.toUnit() = Unit(
    id = id,
    name = name
)

fun Unit.toEntity() = UnitEntity(
    id = id,
    name = name
)