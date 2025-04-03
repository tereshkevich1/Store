package com.example.store.core.data.util

import com.example.store.feature.common.data.local.entity.BarcodeEntity
import com.example.store.feature.common.data.local.entity.PackEntity
import com.example.store.feature.common.data.local.entity.PackPriceEntity
import com.example.store.feature.common.data.local.entity.UnitEntity


object PrepopulateData {
    val packs = listOf(
        PackEntity(unitId = 1, name = "Book", type = 0, quant = 10),
        PackEntity(unitId = 1, name = "Plate", type = 0, quant = 10),
        PackEntity(unitId = 1, name = "Pen", type = 0, quant = 100),
        PackEntity(unitId = 1, name = "Notebook", type = 0, quant = 100),
        PackEntity(unitId = 1, name = "Box of cookies", type = 0, quant = 10),
        PackEntity(unitId = 1, name = "Pack of crayons", type = 0, quant = 120),
        PackEntity(unitId = 1, name = "SNICKERS", type = 0, quant = 100),
        PackEntity(unitId = 1, name = "Carton of eggs", type = 0, quant = 120),
        PackEntity(unitId = 1, name = "Pair of gloves", type = 0, quant = 20),
        PackEntity(unitId = 1, name = "Mug", type = 0, quant = 10),

        PackEntity(unitId = 2, name = "Rice", type = 1, quant = 10000),
        PackEntity(unitId = 2, name = "Flour", type = 1, quant = 20000),
        PackEntity(unitId = 2, name = "Sugar", type = 1, quant = 50000),
        PackEntity(unitId = 2, name = "Salt", type = 1, quant = 25000),
        PackEntity(unitId = 2, name = "Cocoa powder", type = 1, quant = 15000),
        PackEntity(unitId = 2, name = "Green tea", type = 1, quant = 5000),
        PackEntity(unitId = 2, name = "Cucumber", type = 1, quant = 10000),
        PackEntity(unitId = 2, name = "Tomato", type = 1, quant = 1000),
        PackEntity(unitId = 2, name = "Potato", type = 1, quant = 2500000),
    )

    val units = listOf(
        UnitEntity(name = "piece"),
        UnitEntity(name = "gram")
    )

    val packPrices = listOf(
        PackPriceEntity(packId = 1, price = 2500, bonus = 200),
        PackPriceEntity(packId = 2, price = 500, bonus = 0),
        PackPriceEntity(packId = 3, price = 200, bonus = 100),
        PackPriceEntity(packId = 4, price = 500, bonus = 50),
        PackPriceEntity(packId = 5, price = 700, bonus = 300),
        PackPriceEntity(packId = 6, price = 900, bonus = 200),
        PackPriceEntity(packId = 7, price = 250, bonus = 20),
        PackPriceEntity(packId = 8, price = 350, bonus = 0),
        PackPriceEntity(packId = 9, price = 450, bonus = 0),
        PackPriceEntity(packId = 10, price = 700, bonus = 100),
        PackPriceEntity(packId = 11, price = 400, bonus = 100),
        PackPriceEntity(packId = 12, price = 500, bonus = 0),
        PackPriceEntity(packId = 13, price = 300, bonus = 0),
        PackPriceEntity(packId = 14, price = 300, bonus = 0),
        PackPriceEntity(packId = 15, price = 400, bonus = 100),
        PackPriceEntity(packId = 16, price = 500, bonus = 300),
        PackPriceEntity(packId = 17, price = 700, bonus = 50),
        PackPriceEntity(packId = 18, price = 500, bonus = 200),
        PackPriceEntity(packId = 19, price = 900, bonus = 300)
    )

    val barcodes = listOf(
        BarcodeEntity(packId = 1, body = "barcode1"),
        BarcodeEntity(packId = 2, body = "barcode2"),
        BarcodeEntity(packId = 3, body = "barcode3"),
        BarcodeEntity(packId = 4, body = "barcode4"),
        BarcodeEntity(packId = 5, body = "barcode5"),
        BarcodeEntity(packId = 6, body = "barcode6"),
        BarcodeEntity(packId = 7, body = "barcode7"),
        BarcodeEntity(packId = 8, body = "barcode8"),
        BarcodeEntity(packId = 9, body = "barcode9"),
        BarcodeEntity(packId = 10, body = "barcode10"),
        BarcodeEntity(packId = 11, body = "barcode11"),
        BarcodeEntity(packId = 12, body = "barcode12"),
        BarcodeEntity(packId = 13, body = "barcode13"),
        BarcodeEntity(packId = 14, body = "barcode14"),
        BarcodeEntity(packId = 15, body = "barcode15"),
        BarcodeEntity(packId = 16, body = "barcode16"),
        BarcodeEntity(packId = 17, body = "barcode17"),
        BarcodeEntity(packId = 18, body = "barcode18"),
        BarcodeEntity(packId = 19, body = "barcode19")
    )
}