package com.example.store.feature.store.presentation.packs_list_screen

import com.example.store.core.presentation.UiText
import com.example.store.feature.store.presentation.models.PackWithDetailsUi

data class PackListState(
    var showBottomSheet: Boolean = false,
    var selectedPack: PackWithDetailsUi? = null,
    val packList: List<PackWithDetailsUi> = emptyList(),
    val totalPrice: String = "",
    val quantity: String = "",
    val quantityError: UiText? = null
)