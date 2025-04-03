package com.example.store.feature.common.data.local.mappers

import com.example.store.feature.common.data.local.entity.BarcodeEntity
import com.example.store.feature.common.domain.model.Barcode


fun BarcodeEntity.toBarcode() = Barcode(
    id = id,
    packId = packId,
    body = body
)

fun Barcode.toEntity() = BarcodeEntity(
    id = id,
    packId = packId,
    body = body
)