package com.example.store.feature.store.data.repository

import com.example.store.core.data.local.data_source.PackDao
import com.example.store.core.data.local.mappers.toPackWithDetails
import com.example.store.core.domain.model.PackWithDetails
import com.example.store.feature.store.domain.repository.PackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PackRepositoryImpl @Inject constructor(private val dao: PackDao) : PackRepository {
    override suspend fun getAllPacksWithDetails(): Flow<List<PackWithDetails>> =
        dao.getAllPacksWithDetails().map { packs ->
            packs.map {
                it.toPackWithDetails()
            }
        }
}