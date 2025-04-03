package com.example.store.feature.auth.presentation.sign_in

import androidx.lifecycle.viewModelScope
import com.example.store.core.domain.Result
import com.example.store.core.presentation.BaseViewModel
import com.example.store.feature.auth.domain.use_case.SignInUseCase
import com.example.store.feature.auth.domain.use_case.validation.ValidateEmailUseCase
import com.example.store.feature.auth.domain.use_case.validation.ValidatePasswordUseCase
import com.example.store.feature.auth.presentation.errors_ui.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) :
    BaseViewModel<SignInScreenEvent>() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        SignInState()
    )

    fun onAction(action: SignInScreenAction) {
        when (action) {
            is SignInScreenAction.OnEnterEmail -> {
                _state.update {
                    it.copy(emailText = action.value)
                }
            }

            is SignInScreenAction.OnEnterPassword -> {
                _state.update {
                    it.copy(passwordText = action.value)
                }
            }

            SignInScreenAction.OnTogglePasswordVisibility -> {
                _state.update {
                    it.copy(isPasswordVisible = !it.isPasswordVisible)
                }
            }

            SignInScreenAction.OnSignUp -> sendEvent(SignInScreenEvent.NavigateToSignUp)

            SignInScreenAction.OnSignIn -> signIn()
        }
    }

    private fun signIn() {
        val isEmailValid = isEmailValid()
        val isPasswordValid = isPasswordValid()
        if (!isEmailValid || !isPasswordValid) return

        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            val result = signInUseCase(
                email = _state.value.emailText,
                password = _state.value.passwordText
            )
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(isLoading = false)
                    }
                    sendEvent(SignInScreenEvent.ShowToast(result.error.asUiText()))
                }

                is Result.Success -> {
                    _state.update {
                        it.copy(isLoading = false)
                    }
                    sendEvent(SignInScreenEvent.NavigateToPackList)
                }
            }
        }
    }

    private fun isEmailValid(): Boolean {
        return when (val result = validateEmailUseCase(_state.value.emailText)) {
            is Result.Error -> {
                _state.update {
                    it.copy(emailError = result.error.asUiText())
                }
                false
            }

            is Result.Success -> {
                _state.update {
                    it.copy(emailError = null)
                }
                true
            }
        }
    }

    private fun isPasswordValid(): Boolean {
        return when (val result = validatePasswordUseCase(_state.value.passwordText)) {
            is Result.Error -> {
                _state.update {
                    it.copy(passwordError = result.error.asUiText())
                }
                false
            }

            is Result.Success -> {
                _state.update {
                    it.copy(passwordError = null)
                }
                true
            }
        }
    }
}


