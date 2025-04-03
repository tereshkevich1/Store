package com.example.store.feature.common.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "pack",
    foreignKeys = [ForeignKey(
        entity = UnitEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("unit_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class PackEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "unit_id")
    val unitId: Int,
    val name: String,
    val type: Int,
    val quant: Int
)

