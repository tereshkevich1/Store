package com.example.store.feature.profile.presentation.model

import com.example.store.core.data.remote.model.PackFirestore
import com.example.store.core.data.remote.model.Purchase
import com.example.store.feature.store.presentation.models.DisplayableNumber
import com.example.store.feature.store.presentation.models.toFormattedPrice
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class PurchaseUi(
    val items: List<PackFirestore> = emptyList(),
    val totalPrice: DisplayableNumber,
    val timestamp: String
)

fun Purchase.toPurchaseUi() = PurchaseUi(
    items = items,
    totalPrice = totalPrice.toFormattedPrice(),
    timestamp = timestamp.toDateTime()
)

fun Long.toDateTime(pattern: String = "dd.MM.yyyy HH:mm"): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(date)
}

