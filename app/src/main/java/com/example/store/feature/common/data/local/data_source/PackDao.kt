package com.example.store.feature.common.data.local.data_source

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.store.feature.common.data.local.entity.PackEntity
import com.example.store.feature.common.data.local.entity.PackWithDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PackDao {
    @Upsert
    suspend fun upsertPack(packEntity: PackEntity)

    @Upsert
    fun upsertPacks(packs: List<PackEntity>)

    @Query("SELECT * FROM pack ORDER BY name ASC")
    fun getPacksOrderedByName(): Flow<List<PackEntity>>

    @Query(" SELECT * FROM pack ORDER BY name ASC")
    fun getAllPacksWithDetails(): Flow<List<PackWithDetailsEntity>>
}


