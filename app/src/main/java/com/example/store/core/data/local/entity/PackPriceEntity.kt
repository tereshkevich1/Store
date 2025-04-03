package com.example.store.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "pack_price",
    foreignKeys = [ForeignKey(
        entity = PackEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("pack_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class PackPriceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "pack_id")
    val packId: Int,
    val price: Int,
    val bonus: Int
)