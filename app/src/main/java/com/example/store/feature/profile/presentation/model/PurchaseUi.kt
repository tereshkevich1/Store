package com.example.store.feature.profile.presentation.model

import com.example.store.feature.common.data.remote.model.PurchaseSummary
import com.example.store.feature.store.presentation.models.DisplayableNumber
import com.example.store.feature.store.presentation.models.toFormattedPrice
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class PurchaseSummaryUi(
    val documentId: String,
    val totalPrice: DisplayableNumber,
    val timestamp: String
)

fun PurchaseSummary.toPurchaseSummaryUi() = PurchaseSummaryUi(
    documentId = id,
    totalPrice = totalPrice.toFormattedPrice(),
    timestamp = timestamp.toDateTime()
)


fun Long.toDateTime(pattern: String = "dd.MM.yyyy HH:mm"): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(date)
}

