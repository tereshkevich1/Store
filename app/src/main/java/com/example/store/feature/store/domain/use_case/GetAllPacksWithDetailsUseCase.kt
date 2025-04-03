package com.example.store.feature.store.domain.use_case

import com.example.store.feature.store.domain.repository.PackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllPacksWithDetailsUseCase @Inject constructor(
    private val packRepository: PackRepository
) {
    suspend operator fun invoke() =
        withContext(Dispatchers.IO) { packRepository.getAllPacksWithDetails() }
}