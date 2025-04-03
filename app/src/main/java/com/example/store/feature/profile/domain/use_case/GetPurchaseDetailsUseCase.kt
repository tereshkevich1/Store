package com.example.store.feature.profile.domain.use_case

import com.example.store.feature.common.data.remote.errors.FirestoreError
import com.example.store.feature.common.data.remote.model.PackFirestore
import com.example.store.core.domain.Result
import com.example.store.feature.profile.domain.repository.PurchaseHistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPurchaseDetailsUseCase @Inject constructor(private val purchaseHistoryRepository: PurchaseHistoryRepository) {
    suspend operator fun invoke(purchaseId: String): Result<List<PackFirestore>, FirestoreError> {

        return withContext(Dispatchers.IO) {
            when (val result = purchaseHistoryRepository.getPurchase(purchaseId)) {
                is Result.Success -> Result.Success(result.data.items)
                is Result.Error -> Result.Error(result.error)
            }
        }
    }
}