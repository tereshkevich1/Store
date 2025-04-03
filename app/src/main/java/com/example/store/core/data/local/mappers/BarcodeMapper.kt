package com.example.store.core.data.local.mappers

import com.example.store.core.data.local.entity.BarcodeEntity
import com.example.store.core.domain.model.Barcode


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