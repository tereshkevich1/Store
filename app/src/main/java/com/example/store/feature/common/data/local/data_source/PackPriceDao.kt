package com.example.store.feature.common.data.local.data_source

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.store.feature.common.data.local.entity.PackPriceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PackPriceDao {
    @Upsert
    suspend fun upsertPackPrice(packPriceEntity: PackPriceEntity)

    @Upsert
    fun upsertPackPrices(packPrices: List<PackPriceEntity>)

    @Query("SELECT * FROM pack_price")
    fun getPackPrices(): Flow<List<PackPriceEntity>>

    @Query("SELECT * FROM pack_price WHERE pack_id = :packId")
    fun getPackPriceByPackId(packId: Int): PackPriceEntity?
}