package com.example.store.feature.common.data.local.data_source

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.store.feature.common.data.local.entity.BarcodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BarcodeDao {
    @Upsert
    suspend fun upsertBarcode(barcodeEntity: BarcodeEntity)

    @Upsert
    fun upsertBarcodes(barcodes: List<BarcodeEntity>)

    @Query("SELECT * FROM barcode")
    fun getBarcodes(): Flow<List<BarcodeEntity>>

    @Query("SELECT * FROM barcode WHERE id = :packId")
    fun getBarcodeByPackId(packId: Int): BarcodeEntity?
}