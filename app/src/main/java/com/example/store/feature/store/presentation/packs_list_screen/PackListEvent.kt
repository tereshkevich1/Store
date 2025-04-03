package com.example.store.feature.store.presentation.packs_list_screen

sealed interface PackListEvent {
    data class ShowToast(val message: String) : PackListEvent
}

