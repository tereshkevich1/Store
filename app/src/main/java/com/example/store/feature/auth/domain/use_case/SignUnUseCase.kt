package com.example.store.feature.auth.domain.use_case

import com.example.store.core.domain.Result
import com.example.store.feature.auth.domain.repository.AuthRepository
import com.example.store.feature.auth.domain.errors.FirebaseAuthError
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SignUnUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String, username: String): Result<FirebaseUser?, FirebaseAuthError> {
        return authRepository.signUp(email, password, username)
    }
}