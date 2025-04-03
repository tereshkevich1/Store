package com.example.store.feature.auth.presentation.sign_up

import androidx.lifecycle.viewModelScope
import com.example.store.core.domain.Result
import com.example.store.core.presentation.BaseViewModel
import com.example.store.feature.auth.domain.use_case.SignUpUseCase
import com.example.store.feature.auth.domain.use_case.validation.ValidateEmailUseCase
import com.example.store.feature.auth.domain.use_case.validation.ValidatePasswordUseCase
import com.example.store.feature.auth.domain.use_case.validation.ValidateUsernameUseCase
import com.example.store.feature.auth.presentation.errors_ui.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val validateUsernameUseCase: ValidateUsernameUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) :
    BaseViewModel<SignUpScreenEvent>() {
    private val _state = MutableStateFlow(SignUpState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        SignUpState()
    )

    fun onAction(action: SignUpScreenAction) {
        when (action) {
            is SignUpScreenAction.OnEnterEmail -> {
                _state.update {
                    it.copy(emailText = action.value)
                }
            }

            is SignUpScreenAction.OnEnterPassword -> {
                _state.update {
                    it.copy(passwordText = action.value)
                }
            }

            is SignUpScreenAction.OnEnterUsername -> {
                _state.update {
                    it.copy(usernameText = action.value)
                }
            }

            SignUpScreenAction.OnTogglePasswordVisibility -> {
                _state.update {
                    it.copy(isPasswordVisible = !it.isPasswordVisible)
                }
            }

            SignUpScreenAction.OnSignIn -> sendEvent(SignUpScreenEvent.NavigateToSignInScreen)

            SignUpScreenAction.OnSignUp -> signUp()
        }
    }

    private fun signUp() {
        val isEmailValid = isEmailValid()
        val isPasswordValid = isPasswordValid()
        val isUsernameValid = isUsernameValid()
        if (!isEmailValid || !isPasswordValid || !isUsernameValid) return

        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            val result = signUpUseCase(
                email = _state.value.emailText,
                password = _state.value.passwordText,
                username = _state.value.usernameText
            )
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(isLoading = false)
                    }
                    sendEvent(SignUpScreenEvent.ShowToast(result.error.asUiText()))
                }

                is Result.Success -> {
                    _state.update {
                        it.copy(isLoading = false)
                    }
                    sendEvent(SignUpScreenEvent.NavigateToPackList)
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

    private fun isUsernameValid(): Boolean {
        return when (val result = validateUsernameUseCase(_state.value.usernameText)) {
            is Result.Error -> {
                _state.update {
                    it.copy(usernameError = result.error.asUiText())
                }
                false
            }

            is Result.Success -> {
                _state.update {
                    it.copy(usernameError = null)
                }
                true
            }
        }
    }
}
