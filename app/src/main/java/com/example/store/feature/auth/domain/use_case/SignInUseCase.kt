package com.example.store.feature.auth.domain.use_case

import com.example.store.core.domain.Result
import com.example.store.feature.auth.domain.repository.AuthRepository
import com.example.store.feature.auth.domain.errors.FirebaseAuthError
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<FirebaseUser?, FirebaseAuthError> {
        return authRepository.signIn(email, password)
    }
}


