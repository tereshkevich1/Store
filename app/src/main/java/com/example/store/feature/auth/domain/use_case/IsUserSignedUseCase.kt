package com.example.store.feature.auth.domain.use_case

import com.example.store.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class IsUserSignedUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): Boolean{
        return authRepository.isUserSigned()
    }
}