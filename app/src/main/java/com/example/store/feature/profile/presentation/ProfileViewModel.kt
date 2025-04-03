package com.example.store.feature.profile.presentation

import androidx.lifecycle.viewModelScope
import com.example.store.core.domain.Result
import com.example.store.core.presentation.BaseViewModel
import com.example.store.core.presentation.errors_ui.asUiText
import com.example.store.feature.profile.domain.use_case.GetUserNameUseCase
import com.example.store.feature.profile.domain.use_case.GetUserPurchasesSortedByTimeUseCase
import com.example.store.feature.profile.presentation.model.toPurchaseUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val getUserPurchasesSortedByTimeUseCase: GetUserPurchasesSortedByTimeUseCase
) : BaseViewModel<ProfileScreeEvent>() {

    private val _state = MutableStateFlow(ProfileScreenState())
    val state = _state
        .onStart { loadUserInfo() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            ProfileScreenState()
        )

    fun onAction(action: ProfileScreenAction) {
        when (action) {
            ProfileScreenAction.OnPurchaseHistoryItemClick -> {}
        }
    }

    private fun loadUserInfo() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val purchasesResult = async { getUserPurchasesSortedByTimeUseCase() }
            val usernameResult = async { getUserNameUseCase() }

            val purchases = purchasesResult.await()
            val username = usernameResult.await()

            when {
                purchases is Result.Error -> {
                    sendEvent(ProfileScreeEvent.ShowToast(purchases.error.asUiText()))
                }

                username is Result.Error -> {
                    sendEvent(ProfileScreeEvent.ShowToast(username.error.asUiText()))
                }
            }

            _state.update { state ->
                state.copy(
                    purchaseList = (purchases as? Result.Success)?.data?.map { it.toPurchaseUi() }
                        ?: emptyList(),
                    username = (username as? Result.Success)?.data.orEmpty(),
                    isLoading = false
                )
            }
        }
    }
}


