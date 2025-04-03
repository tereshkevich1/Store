package com.example.store.feature.profile.domain.use_case

import com.example.store.feature.common.data.remote.errors.FirestoreError
import com.example.store.core.domain.Result
import com.example.store.feature.profile.domain.repository.PurchaseHistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserNameUseCase @Inject constructor(private val purchaseHistoryRepository: PurchaseHistoryRepository) {
    suspend operator fun invoke(): Result<String, FirestoreError> =
        withContext(Dispatchers.IO) {
            purchaseHistoryRepository.getUserName()
        }
}