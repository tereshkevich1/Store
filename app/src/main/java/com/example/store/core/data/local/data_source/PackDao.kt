package com.example.store.core.data.local.data_source

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.store.core.data.local.entity.PackEntity
import com.example.store.core.data.local.entity.PackWithDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PackDao {
    @Upsert
    suspend fun upsertPack(packEntity: PackEntity)

    @Upsert
    fun upsertPacks(packs: List<PackEntity>)

    @Query("SELECT * FROM pack ORDER BY name ASC")
    fun getPacksOrderedByName(): Flow<List<PackEntity>>

    @Query(" SELECT p.id AS pack_id,\n" +
            "            p.unit_id AS pack_unit_id,\n" +
            "            p.name AS pack_name,\n" +
            "            p.type AS pack_type,\n" +
            "            p.quant AS pack_quant,\n" +
            "            pp.id AS price_id,\n" +
            "            pp.pack_id AS price_pack_id,\n" +
            "            pp.price AS price_price,\n" +
            "            pp.bonus AS price_bonus,\n" +
            "            u.id AS unit_id,\n" +
            "            u.name AS unit_name FROM pack p LEFT JOIN pack_price pp ON p.id = pp.pack_id LEFT JOIN unit u ON p.unit_id = u.id")
    fun getAllPacksWithDetails(): Flow<List<PackWithDetailsEntity>>
}


