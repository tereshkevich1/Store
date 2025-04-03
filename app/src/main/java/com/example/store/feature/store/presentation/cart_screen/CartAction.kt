package com.example.store.feature.store.presentation.cart_screen

sealed interface CartAction {
    data object OnCheckoutCart : CartAction
}