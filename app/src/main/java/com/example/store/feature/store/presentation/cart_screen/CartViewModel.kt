package com.example.store.feature.store.presentation.cart_screen

import androidx.lifecycle.viewModelScope
import com.example.store.core.domain.Result
import com.example.store.core.presentation.BaseViewModel
import com.example.store.feature.store.domain.use_case.cart.CheckoutCartUseCase
import com.example.store.feature.store.domain.use_case.cart.GetCartListUseCase
import com.example.store.feature.store.domain.use_case.cart.GetCartTotalPriceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartListUseCase: GetCartListUseCase,
    private val checkoutCartUseCase: CheckoutCartUseCase,
    private val getCartTotalPriceUseCase: GetCartTotalPriceUseCase
) : BaseViewModel<CartEvent>() {

    private val _state = MutableStateFlow(CartState())
    val state = _state
        .onStart {
            loadCartList()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CartState()
        )

    fun onAction(action: CartAction) {
        when (action) {
            CartAction.OnCheckoutCart -> checkoutCart()
        }
    }

    private fun checkoutCart() {
        viewModelScope.launch {
            val result = checkoutCartUseCase(_state.value.packList)
            when (result) {
                is Result.Error -> {
                    sendEvent(CartEvent.ShowToast(""))
                }

                is Result.Success -> {
                    sendEvent(CartEvent.ShowToast("success"))
                }
            }
        }
    }

    private fun loadCartList() {
        viewModelScope.launch {
            getCartListUseCase().collect { cartList ->
                val totalPrice = getCartTotalPriceUseCase(cartList)
                _state.update { state ->
                    state.copy(
                        packList = cartList,
                        totalPrice = totalPrice
                    )
                }
            }
        }
    }
}

