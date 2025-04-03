package com.example.store.feature.store.presentation.cart_screen

sealed interface CartEvent {
    data class ShowToast(val message: String) : CartEvent
}