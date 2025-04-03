package com.example.store.feature.auth.domain.repository

import com.example.store.core.domain.Result
import com.example.store.feature.auth.domain.errors.FirebaseAuthError
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun isUserSigned(): Boolean
    suspend fun signIn(email: String, password: String): Result<FirebaseUser?, FirebaseAuthError>
    suspend fun signUp(email: String, password: String, username: String): Result<FirebaseUser?, FirebaseAuthError>
}