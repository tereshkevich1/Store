package com.example.store.feature.profile.domain.use_case

import com.example.store.feature.common.data.remote.errors.FirestoreError
import com.example.store.feature.common.data.remote.model.PurchaseSummary
import com.example.store.core.domain.Result
import com.example.store.feature.profile.domain.repository.PurchaseHistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserPurchasesSortedByTimeUseCase @Inject constructor(private val purchaseHistoryRepository: PurchaseHistoryRepository) {
    suspend operator fun invoke(): Result<List<PurchaseSummary>, FirestoreError> {
        return withContext(Dispatchers.IO) {
            when (val result = purchaseHistoryRepository.getPurchaseSummaries()) {
                is Result.Success -> Result.Success(result.data.sortedByDescending { it.timestamp })
                is Result.Error -> result
            }
        }
    }
}

