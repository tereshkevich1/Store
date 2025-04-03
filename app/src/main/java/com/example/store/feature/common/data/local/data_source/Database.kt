package com.example.store.feature.common.data.local.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.store.feature.common.data.local.entity.BarcodeEntity
import com.example.store.feature.common.data.local.entity.PackEntity
import com.example.store.feature.common.data.local.entity.PackPriceEntity
import com.example.store.feature.common.data.local.entity.UnitEntity

@Database(
    entities = [PackEntity::class, PackPriceEntity::class,
        UnitEntity::class, BarcodeEntity::class],
    version = 1
)
abstract class StoreDatabase : RoomDatabase() {

    abstract fun packDao(): PackDao
    abstract fun packPriceDao(): PackPriceDao
    abstract fun unitDao(): UnitDao
    abstract fun barcodeDao(): BarcodeDao

    companion object {
        const val DATABASE_NAME = "store_db"
    }
}