package com.example.store.feature.common.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.feature.auth.domain.use_case.IsUserSignedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthCheckViewModel @Inject constructor(private val isUserSignedUseCase: IsUserSignedUseCase) :
    ViewModel() {
    var isLoggedIn by mutableStateOf(false)

     init {
         checkAuthStatus()
     }

    private fun checkAuthStatus() {
        viewModelScope.launch {
            if (!isUserSignedUseCase()) return@launch
            isLoggedIn = true
        }
    }
}