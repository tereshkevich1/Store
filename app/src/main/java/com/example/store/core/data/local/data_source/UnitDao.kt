package com.example.store.core.data.local.data_source

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.store.core.data.local.entity.UnitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UnitDao {
    @Upsert
    suspend fun upsertUnit(unitEntity: UnitEntity)

    @Upsert
    fun upsertUnits(units: List<UnitEntity>)

    @Query("SELECT * FROM unit")
    fun getUnits(): Flow<List<UnitEntity>>

    @Query("SELECT * FROM unit WHERE id = :id")
    fun getUnitById(id: Int): UnitEntity?
}