package com.example.store.feature.auth.data

import android.util.Log
import com.example.store.core.domain.Result
import com.example.store.feature.common.data.remote.FirebaseDataSource
import com.example.store.feature.auth.domain.errors.FirebaseAuthError
import com.example.store.feature.auth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firebaseDataSource: FirebaseDataSource
) : AuthRepository {
    override suspend fun isUserSigned(): Boolean {
        val currentUser = auth.currentUser
        return currentUser != null
    }

    override suspend fun signUp(
        email: String,
        password: String,
        username: String
    ): Result<FirebaseUser?, FirebaseAuthError> {
        return try {
            val user = auth.createUserWithEmailAndPassword(email, password).await().user
                ?: throw Exception("")
            firebaseDataSource.saveUserToFirestore(user.uid, username)
            Result.Success(data = user)
        } catch (e: FirebaseAuthException) {
            Log.d(TAG, "${e.errorCode} - ${e.message}")

            val error = when (e.errorCode) {
                "ERROR_INVALID_EMAIL" -> FirebaseAuthError.INVALID_EMAIL
                "ERROR_EMAIL_ALREADY_IN_USE" -> FirebaseAuthError.EMAIL_ALREADY_IN_USE
                "ERROR_WEAK_PASSWORD" -> FirebaseAuthError.WEAK_PASSWORD
                "ERROR_NETWORK_REQUEST_FAILED" -> FirebaseAuthError.NETWORK_ERROR
                else -> FirebaseAuthError.UNKNOWN_ERROR
            }

            Result.Error(error)
        } catch (e: Exception) {
            Log.d(TAG, "${e.message} - Unexpected error")
            Result.Error(FirebaseAuthError.UNKNOWN_ERROR)
        }
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): Result<FirebaseUser?, FirebaseAuthError> {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password).await().user
            Result.Success(data = user)
        } catch (e: FirebaseAuthException) {
            Log.d(TAG, "${e.errorCode} - ${e.message}")

            val error = when (e.errorCode) {
                "ERROR_WRONG_PASSWORD" -> FirebaseAuthError.WRONG_PASSWORD
                "ERROR_INVALID_CREDENTIAL" -> FirebaseAuthError.USER_NOT_FOUND
                "ERROR_NETWORK_REQUEST_FAILED" -> FirebaseAuthError.NETWORK_ERROR
                else -> FirebaseAuthError.UNKNOWN_ERROR
            }

            Result.Error(error)
        } catch (e: Exception) {
            Log.d(TAG, "${e.message} - Unexpected error")
            Result.Error(FirebaseAuthError.UNKNOWN_ERROR)
        }
    }

    companion object {
        const val TAG = "AuthRepositoryImpl"
    }
}