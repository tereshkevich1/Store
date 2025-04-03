package com.example.store.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "barcode",
    foreignKeys = [ForeignKey(
        entity = PackEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("pack_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class BarcodeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "pack_id")
    val packId: Int,
    val body: String
)