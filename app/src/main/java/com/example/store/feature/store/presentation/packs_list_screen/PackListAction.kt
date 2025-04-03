package com.example.store.feature.store.presentation.packs_list_screen

import com.example.store.feature.store.presentation.models.PackWithDetailsUi

sealed interface PackListAction {
    data class OnPackClick(val pack: PackWithDetailsUi) : PackListAction
    data class OnAddPackToCart(val pack: PackWithDetailsUi, val quantity: String) : PackListAction
    data class OnQuantityChanged(val value: String): PackListAction
    data object OnBottomSheetClose : PackListAction
}