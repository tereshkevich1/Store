package com.example.store.feature.store.domain.repository

import com.example.store.feature.common.domain.model.PackWithDetails
import kotlinx.coroutines.flow.Flow

interface PackRepository {
    suspend fun getAllPacksWithDetails(): Flow<List<PackWithDetails>>
}