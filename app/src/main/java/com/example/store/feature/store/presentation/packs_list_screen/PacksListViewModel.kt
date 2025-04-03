package com.example.store.feature.store.presentation.packs_list_screen

import androidx.lifecycle.viewModelScope
import com.example.store.core.domain.Result
import com.example.store.core.presentation.BaseViewModel
import com.example.store.feature.store.domain.use_case.cart.AddPackToCartUseCase
import com.example.store.feature.store.domain.use_case.CalculateItemTotalPriceUseCase
import com.example.store.feature.store.domain.use_case.GetAllPacksWithDetailsUseCase
import com.example.store.feature.store.domain.use_case.validations.ValidateProductQuantityUseCase
import com.example.store.feature.store.presentation.models.PackWithDetailsUi
import com.example.store.feature.store.presentation.models.toFormattedPrice
import com.example.store.feature.store.presentation.models.toPackWithDetailsUi
import com.example.store.feature.store.presentation.packs_list_screen.bottom_sheet.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PacksListViewModel @Inject constructor(
    private val getAllPacksWithDetailsUseCase: GetAllPacksWithDetailsUseCase,
    private val validateProductQuantityUseCase: ValidateProductQuantityUseCase,
    private val calculateItemTotalPriceUseCase: CalculateItemTotalPriceUseCase,
    private val addPackToCartUseCase: AddPackToCartUseCase
) : BaseViewModel<PackListEvent>() {

    private val _state = MutableStateFlow(PackListState())
    val state = _state
        .onStart { loadPacks() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            PackListState()
        )

    fun onAction(action: PackListAction) {
        when (action) {
            is PackListAction.OnPackClick -> selectPack(action.pack)
            PackListAction.OnBottomSheetClose -> closeBottomSheet()
            is PackListAction.OnQuantityChanged -> updateQuantity(action.value)
            is PackListAction.OnAddPackToCart -> {
                addPackToCart(action.pack, action.quantity)
                closeBottomSheet()
            }
        }
    }

    private fun selectPack(pack: PackWithDetailsUi) {
        _state.update { currentState ->
            currentState.copy(
                selectedPack = pack,
                showBottomSheet = true
            )
        }
    }

    private fun closeBottomSheet() {
        _state.update { currentState ->
            currentState.copy(
                showBottomSheet = false,
                quantity = "",
                totalPrice = "",
                quantityError = null
            )
        }
    }

    private fun updateQuantity(input: String) {
        val pack = _state.value.selectedPack ?: return
        val result = validateProductQuantityUseCase(input, pack.quant)
        _state.update { currentState ->
            when (result) {
                is Result.Success -> {
                    val quantity = result.data

                    currentState.copy(
                        quantity = quantity?.toString() ?: "",
                        totalPrice = calculateItemTotalPriceUseCase(
                            quantity = quantity ?: 0,
                            discountedPrice = pack.discountedPrice.value,
                            type = pack.type
                        ).toFormattedPrice().formatted,
                        quantityError = null
                    )
                }

                is Result.Error -> currentState.copy(
                    quantity = input,
                    quantityError = result.error.asUiText(),
                )
            }
        }
    }

    private fun addPackToCart(pack: PackWithDetailsUi, quantity: String) {
        val quantityInt = quantity.toIntOrNull() ?: return
        viewModelScope.launch {
            addPackToCartUseCase(pack, quantityInt)
            sendEvent(PackListEvent.ShowToast(""))
        }
    }

    private fun loadPacks() {
        viewModelScope.launch {
            getAllPacksWithDetailsUseCase().collect { packs ->
                _state.update { state ->
                    state.copy(
                        packList = packs.map {
                            it.toPackWithDetailsUi()
                        }
                    )
                }
            }
        }
    }
}

