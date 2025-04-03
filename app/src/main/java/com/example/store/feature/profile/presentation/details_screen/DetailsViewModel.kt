package com.example.store.feature.profile.presentation.details_screen

import androidx.lifecycle.viewModelScope
import com.example.store.core.domain.Result
import com.example.store.core.presentation.BaseViewModel
import com.example.store.feature.common.presentation.errors_ui.asUiText
import com.example.store.feature.profile.domain.use_case.GetPurchaseDetailsUseCase
import com.example.store.feature.profile.presentation.model.toPackFirestoreUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPurchaseDetailsUseCase: GetPurchaseDetailsUseCase,
) : BaseViewModel<DetailsScreeEvent>() {

    private val _state = MutableStateFlow(DetailsScreenState())
    val state = _state
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            DetailsScreenState()
        )

    fun onAction(action: DetailsScreeAction) {
        when (action) {
            DetailsScreeAction.OnPurchaseHistoryItemClick -> {}
        }
    }

    fun loadDetails(purchaseId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            when (val result = getPurchaseDetailsUseCase(purchaseId)) {
                is Result.Error -> sendEvent(DetailsScreeEvent.ShowToast(result.error.asUiText()))
                is Result.Success -> {
                    _state.update { state ->
                        state.copy(
                            purchaseList = result.data.map {
                                it.toPackFirestoreUi()
                            },
                            isLoading = false
                        )
                    }
                }
            }

        }
    }
}


